package com.unbosque.edu.co.forest.service;

import com.unbosque.edu.co.forest.exception.CustomAlpacaException;
import com.unbosque.edu.co.forest.model.dto.OrderRequestDTO;
import com.unbosque.edu.co.forest.model.dto.StockDTO;
import com.unbosque.edu.co.forest.model.dto.UserDTO;
import com.unbosque.edu.co.forest.model.entity.Order;
import com.unbosque.edu.co.forest.model.enums.OrderStatus;
import com.unbosque.edu.co.forest.model.enums.OrderType;
import com.unbosque.edu.co.forest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

    @Value("${alpaca.broker.api.key}")
    private String alpacaApiKey;

    @Value("${alpaca.broker.api.secret}")
    private String alpacaApiSecret;

    @Value("${alpaca.broker.url}")
    private String alpacaApiUrl;

    @Value("${platform.commission.percentage}")
    private float commissionPercentage;

    private final RestTemplate restTemplate;
    private final OrderRepository orderRepository;

    public OrderService(@Qualifier("brokerRestTemplate") RestTemplate restTemplate, OrderRepository orderRepository) {
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
    }

    public Order createBuyOrder(OrderRequestDTO request) {
        StockDTO stock = request.getStock();
        UserDTO user = request.getUser();
        float pricePerStock = stock.getCurrentPrice();
        int quantity = request.getQuantity();
        float total = pricePerStock * quantity;
        float platformCommission = total * commissionPercentage;
        float finalAmount = total + platformCommission;
        float limitPrice = request.getLimitPrice();
        float stopPrice = request.getStopPrice();

        // Construir body para Alpaca
        Map<String, Object> body = new HashMap<>();
        body.put("symbol", stock.getSymbol());
        body.put("qty", quantity);
        body.put("side", "buy");
        body.put("type", request.getOrderType().name().toLowerCase());
        body.put("time_in_force", request.getTimeInForce().name().toLowerCase());

        if (request.getOrderType() == OrderType.LIMIT || request.getOrderType() == OrderType.STOP_LIMIT) {
            body.put("limit_price", limitPrice);
        }

        if (request.getOrderType() == OrderType.STOP || request.getOrderType() == OrderType.STOP_LIMIT) {
            body.put("stop_price", stopPrice);
        }


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(alpacaApiKey, alpacaApiSecret);

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(body, headers);

        try {
            // Enviar orden a Alpaca
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    alpacaApiUrl + "/v1/trading/accounts/" + user.getAlpacaAccountId() + "/orders",
                    httpEntity,
                    Map.class
            );

            Map<String, Object> responseBody = response.getBody();

            Order order = new Order();
            order.setAlpacaOrderId((String) responseBody.get("id"));
            order.setUserId(request.getUser().getId());
            order.setOrderType(request.getOrderType());
            order.setSymbol(stock.getSymbol());
            order.setQuantity(quantity);
            order.setTimeInForce(request.getTimeInForce());
            order.setLimitPrice(limitPrice);
            order.setStopPrice(stopPrice);
            order.setFilled_price(total);
            order.setTotalAmountPaid(finalAmount);
            order.setPlatformCommission(platformCommission);
            order.setBrokerCommission(0); // Modificar cuando integren comisionista
            order.setOrderStatus(OrderStatus.SENDED);
            order.setRequiresSignature(false);
            order.setCreatedAt(LocalDateTime.now());
            order.setSentToAlpacaAt(LocalDateTime.now());
            order.setSignedBy(null);

            return orderRepository.save(order);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
        String responseMessage = ex.getResponseBodyAsString();
        throw new CustomAlpacaException(ex.getRawStatusCode(), responseMessage);
    } catch (RestClientException ex) {
        throw new CustomAlpacaException(500, "Error al conectar con Alpaca: " + ex.getMessage());
    }

}
}

