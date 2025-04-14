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

    @Value("${alpaca.market.url}")
    private String marketBaseUrl;

    @Value("${alpaca.broker.url}")
    private String brokerBaseUrl;

    @Value("${financial.api.url}")
    private String fmpApiUrl;

    @Value("${financial.api.key}")
    private String fmpApiKey;

    private final RestTemplate brokerRestTemplate;
    private final RestTemplate marketRestTemplate;
    private final MarketRepository marketRepository;
    private final RestTemplate fmpRestTemplate;
    private final ObjectMapper objectMapper;

    public StockService(@Qualifier("brokerRestTemplate") RestTemplate brokerRestTemplate,
                        @Qualifier("marketRestTemplate") RestTemplate marketRestTemplate,
                        MarketRepository marketRepository, ObjectMapper objectMapper) {
        this.brokerRestTemplate = brokerRestTemplate;
        this.marketRestTemplate = marketRestTemplate;
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
                .limit(10) // Para pruebas solo unos cuantos
                .toList();

        List<StockDTO> result = new ArrayList<>();

        for (Map<String, Object> asset : filteredAssets) {
            String symbol = (String) asset.get("symbol");
            String name = (String) asset.get("name");
            String status = (String) asset.get("status");
            String exchange = (String) asset.get("exchange");

            Optional<Market> marketOpt = marketRepository.findByMarketCode(exchange);
            MarketDTO marketDTO = marketOpt.map(m -> new MarketDTO(m.getId(), m.getMarketCode())).orElse(null);

            float price = 0;
            float volume = 0;
            try {
                Map<String, Object> marketData = marketRestTemplate.getForObject(
                        marketBaseUrl + "/v2/stocks/bars/latest?symbols=" + symbol, Map.class);

                if (marketData != null && marketData.containsKey("bars")) {
                    Map<String, Object> bars = (Map<String, Object>) marketData.get("bars");
                    if (bars.containsKey(symbol)) {
                        Map<String, Object> data = (Map<String, Object>) bars.get(symbol);
                        price = data.get("c") != null ? Float.parseFloat(data.get("c").toString()) : 0;
                        volume = data.get("v") != null ? Float.parseFloat(data.get("v").toString()) : 0;
                    }
                }
            } catch (Exception ignored) {
            }

            float marketCap = 0;
            String sector = "";
            try {
                Map<String, Object>[] profile = fmpRestTemplate.getForObject(fmpApiUrl + symbol + "&apikey="+fmpApiKey, Map[].class);
                if (profile != null && profile.length > 0) {
                    marketCap = profile[0].get("marketCap") != null ? Float.parseFloat(profile[0].get("marketCap").toString()) : 0;
                    sector = profile[0].get("sector") != null ? profile[0].get("sector").toString() : "";
                }
            } catch (Exception ignored) {
            }

            StockDTO dto = new StockDTO(symbol, name, sector, price, volume, marketCap, status, marketDTO);
            result.add(dto);
        }

        return result;

    }
}
