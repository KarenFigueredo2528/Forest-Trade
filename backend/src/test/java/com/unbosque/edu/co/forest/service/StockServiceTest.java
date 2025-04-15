package com.unbosque.edu.co.forest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unbosque.edu.co.forest.model.dto.StockDTO;
import com.unbosque.edu.co.forest.model.entity.Market;
import com.unbosque.edu.co.forest.repository.MarketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class StockServiceTest {

    @Mock
    private RestTemplate brokerRestTemplate;

    @Mock
    private RestTemplate fmpRestTemplate;


    @Mock
    private MarketRepository marketRepository;

    @InjectMocks
    private StockService stockService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(stockService, "brokerBaseUrl", "https://fake-broker-url.com");
        ReflectionTestUtils.setField(stockService, "fmpApiUrl", "https://fake-fmp.com/profile/");
        ReflectionTestUtils.setField(stockService, "fmpApiKey", "fake-key");
        ReflectionTestUtils.setField(stockService, "fmpRestTemplate", fmpRestTemplate);
        ReflectionTestUtils.setField(stockService, "objectMapper", objectMapper);
    }


    @Test
    void testGetAllStocksSuccess() throws Exception {
        // Arrange
        List<Map<String, Object>> fakeAssets = List.of(Map.of(
                "symbol", "AAPL",
                "name", "Apple Inc.",
                "status", "active",
                "tradable", true,
                "exchange", "NASDAQ"
        ));

        when(brokerRestTemplate.getForObject(contains("/v1/assets"), eq(String.class)))
                .thenReturn(objectMapper.writeValueAsString(fakeAssets));

        Market fakeMarket = new Market();
        fakeMarket.setId(1);
        fakeMarket.setMarketCode("NASDAQ");

        when(marketRepository.findByMarketCode("NASDAQ")).thenReturn(Optional.of(fakeMarket));

        Map<String, Object>[] fakeFmpProfiles = new Map[] {
                Map.of(
                        "symbol", "AAPL",
                        "mktCap", 300000000,
                        "changes", 2.5,
                        "sector", "Tech",
                        "industry", "Consumer Electronics",
                        "price", 230.0,
                        "volAvg", 50500000
                )
        };

        when(fmpRestTemplate.getForObject(anyString(), eq(Map[].class)))
                .thenReturn(fakeFmpProfiles);

        // Act
        List<StockDTO> result = stockService.getAllStocks();

        // Assert
        assertEquals(1, result.size());
        assertEquals("AAPL", result.get(0).getSymbol());
        assertEquals("Apple Inc.", result.get(0).getStockName());
        assertEquals(2.5f, result.get(0).getChange());
    }

    @Test
    void shouldHandleNullProfilesGracefully() throws Exception {
        // Arrange
        List<Map<String, Object>> fakeAssets = List.of(Map.of(
                "symbol", "MSFT",
                "name", "Microsoft Corporation",
                "status", "active",
                "tradable", true,
                "exchange", "NASDAQ"
        ));

        when(brokerRestTemplate.getForObject(contains("/v1/assets"), eq(String.class)))
                .thenReturn(objectMapper.writeValueAsString(fakeAssets));

        // Simular que el market sí existe
        Market market = new Market();
        market.setId(2);
        market.setMarketCode("NASDAQ");
        when(marketRepository.findByMarketCode("NASDAQ")).thenReturn(Optional.of(market));

        // Simular que la API FMP devuelve null
        when(fmpRestTemplate.getForObject(anyString(), eq(Map[].class)))
                .thenReturn(null);

        // Act
        List<StockDTO> result = stockService.getAllStocks();

        // Assert
        assertEquals(1, result.size());
        StockDTO dto = result.get(0);

        assertEquals("MSFT", dto.getSymbol());
        assertEquals("Microsoft Corporation", dto.getStockName());

        // Valores por defecto
        assertEquals(0, dto.getMarketCapitalization());
        assertEquals(0, dto.getChange());
        assertEquals("", dto.getSector());
        assertEquals("", dto.getIndustry());
    }

}
