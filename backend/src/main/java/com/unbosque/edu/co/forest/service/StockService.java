package com.unbosque.edu.co.forest.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.unbosque.edu.co.forest.model.dto.MarketDTO;
import com.unbosque.edu.co.forest.model.dto.StockDTO;
import com.unbosque.edu.co.forest.model.entity.Market;
import com.unbosque.edu.co.forest.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class StockService {


    @Value("${alpaca.broker.url}")
    private String brokerBaseUrl;

    @Value("${financial.api.url}")
    private String fmpApiUrl;

    @Value("${financial.api.key}")
    private String fmpApiKey;

    private final RestTemplate brokerRestTemplate;
    private final MarketRepository marketRepository;
    private final RestTemplate fmpRestTemplate;
    private final ObjectMapper objectMapper;

    public StockService(@Qualifier("brokerRestTemplate") RestTemplate brokerRestTemplate,
                        MarketRepository marketRepository, ObjectMapper objectMapper) {
        this.brokerRestTemplate = brokerRestTemplate;
        this.marketRepository = marketRepository;
        this.objectMapper = objectMapper;
        this.fmpRestTemplate = new RestTemplate();
    }

    public List<StockDTO> getAllStocks() throws Exception {
        String assetsUrl = brokerBaseUrl + "/v1/assets?asset_class=us_equity";
        String response = brokerRestTemplate.getForObject(assetsUrl, String.class);
        List<Map<String, Object>> assets = objectMapper.readValue(response, new TypeReference<>() {
        });

        // Filtrar assets
        List<Map<String, Object>> filteredAssets = assets.stream()
                .filter(asset -> "active".equals(asset.get("status"))
                        && Boolean.TRUE.equals(asset.get("tradable"))
                        && Arrays.asList("NASDAQ", "NYSE", "AMEX").contains(asset.get("exchange")))
                .limit(20)
                .toList();

        List<String> symbols = filteredAssets.stream()
                .map(asset -> (String) asset.get("symbol"))
                .toList();


        // 🔹 2. Obtener datos de FMP (una sola vez)
        Map<String, Object>[] profiles = fmpRestTemplate.getForObject(
                fmpApiUrl + String.join(",", symbols) + "?&apikey=" + fmpApiKey,
                Map[].class
        );

        // Mapear perfiles por símbolo
        Map<String, Map<String, Object>> profileMap = new HashMap<>();
        if (profiles != null) {
            for (Map<String, Object> p : profiles) {
                profileMap.put((String) p.get("symbol"), p);
            }
        }

        // 🔹 3. Construir DTOs
        List<StockDTO> result = new ArrayList<>();
        for (Map<String, Object> asset : filteredAssets) {
            String symbol = (String) asset.get("symbol");
            String name = (String) asset.get("name");
            String status = (String) asset.get("status");
            String exchange = (String) asset.get("exchange");

            // Market
            Optional<Market> marketOpt = marketRepository.findByMarketCode(exchange);
            MarketDTO marketDTO = marketOpt.map(m -> new MarketDTO(m.getId(), m.getMarketCode())).orElse(null);


            // FMP data
            float price = 0, volume = 0, marketCap = 0, change = 0;
            String sector = "", industry = "";
            Map<String, Object> profile = profileMap.get(symbol);
            if (profile != null) {
                price = profile.get("price") != null ? Float.parseFloat(profile.get("price").toString()) : 0;
                volume = profile.get("volAvg") != null ? Float.parseFloat(profile.get("volAvg").toString()) : 0;
                marketCap = profile.get("mktCap") != null ? Float.parseFloat(profile.get("mktCap").toString()) : 0;
                change = profile.get("changes") != null ? Float.parseFloat(profile.get("changes").toString()) : 0;
                sector = profile.get("sector") != null ? profile.get("sector").toString() : "";
                industry = profile.get("industry") != null ? profile.get("industry").toString() : "";
            }

            StockDTO dto = new StockDTO(symbol, name, change, sector, industry, price, volume, marketCap, status, marketDTO);
            result.add(dto);
        }

        return result;
    }

}
