package com.unbosque.edu.co.forest.service;

import com.unbosque.edu.co.forest.exception.CustomAlpacaException;
import com.unbosque.edu.co.forest.model.dto.OrderRequestDTO;
import com.unbosque.edu.co.forest.model.dto.StockDTO;
import com.unbosque.edu.co.forest.model.dto.UserDTO;
import com.unbosque.edu.co.forest.model.dto.UserSessionDTO;
import com.unbosque.edu.co.forest.model.entity.Order;
import com.unbosque.edu.co.forest.model.enums.OrderStatus;
import com.unbosque.edu.co.forest.model.enums.OrderType;
import com.unbosque.edu.co.forest.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
        UserSessionDTO user = request.getUser();
        float pricePerStock = stock.getCurrentPrice();
        int quantity = request.getQuantity();
        float total = pricePerStock * quantity;
        float commision = total * commissionPercentage;
        float platformCommission = Math.round(commision * 100.0f) / 100.0f;

        float finalAmount = total + platformCommission;



        Map<String, Object> bodyOrder = buildAlpacaOrderBody(request);
        HttpHeaders headers = buildAlpacaHeaders();
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(bodyOrder, headers);

        try {
            // Comprobar capacidad de pago
            Map<String, Object> responseBodyBuyingPower = getBuyingPower(user.getAlpacaAccountId(), headers);
            float buyingPower = responseBodyBuyingPower.get("buying_power") != null
                    ? Float.parseFloat(responseBodyBuyingPower.get("buying_power").toString())
                    : 0;



            if (buyingPower < finalAmount) {
                throw new CustomAlpacaException(400, "Insufficient buying power to complete the order and pay the platform commission.");
            }

            // Enviar orden a Alpaca

            Map<String, Object> responseBodyOrder = sendOrderToAlpaca(user.getAlpacaAccountId(), httpEntity);
            String alpacaOrderId = (String) responseBodyOrder.get("id");

            //Obtener ultimo estado de la orden
            Map<String, Object> updatedOrder = getAlpacaOrderStatus(user.getAlpacaAccountId(), alpacaOrderId, headers);
            String updatedStatus = (String) updatedOrder.get("status");



            Map<String, Object> transferOutgoing = makeTransferOutgoing(user.getAlpacaAccountId(), headers, user.getBankRelationshipId(), platformCommission, alpacaOrderId);

            float filledPrice = updatedOrder.get("filled_avg_price") != null
                    ? Float.parseFloat(updatedOrder.get("filled_avg_price").toString())
                    : 0;

            float finalFilledPrice = "filled".equalsIgnoreCase(updatedStatus) && filledPrice > 0
                    ? filledPrice * quantity
                    : total;

            float finalAmountPaid = finalFilledPrice + platformCommission;

            return saveOrderInDatabase(request, alpacaOrderId, updatedStatus.toUpperCase(), finalFilledPrice, finalAmountPaid, platformCommission, null);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            String responseMessage = ex.getResponseBodyAsString();
            throw new CustomAlpacaException(ex.getRawStatusCode(), responseMessage);
        } catch (RestClientException ex) {
            throw new CustomAlpacaException(500, "Error al conectar con Alpaca: " + ex.getMessage());
        }
    }

    private Map<String, Object> buildAlpacaOrderBody (OrderRequestDTO request) {
        Map<String, Object> bodyOrder = new HashMap<>();
        bodyOrder.put("symbol", request.getStock().getSymbol());
        bodyOrder.put("qty", request.getQuantity());
        bodyOrder.put("side", "buy");
        bodyOrder.put("type", request.getOrderType().name().toLowerCase());
        bodyOrder.put("time_in_force", request.getTimeInForce().name().toLowerCase());

        if (request.getOrderType() == OrderType.LIMIT || request.getOrderType() == OrderType.STOP_LIMIT) {
            bodyOrder.put("limit_price", request.getLimitPrice());
        }

        if (request.getOrderType() == OrderType.STOP || request.getOrderType() == OrderType.STOP_LIMIT) {
            if (request.getStopPrice() <= 0) {
                throw new CustomAlpacaException(400, "stop_price must be greater than 0");
            }
            bodyOrder.put("stop_price", request.getStopPrice());
        }

        return bodyOrder;
    }

    private HttpHeaders buildAlpacaHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(alpacaApiKey, alpacaApiSecret);
        return headers;
    }


    private Map<String, Object> sendOrderToAlpaca(String accountId, HttpEntity<Map<String, Object>> entity) {
        ResponseEntity<Map> response = restTemplate.postForEntity(
                alpacaApiUrl + "/v1/trading/accounts/" + accountId + "/orders",
                entity,
                Map.class
        );
        return response.getBody();
    }

    private Map<String, Object> getAlpacaOrderStatus(String accountId, String orderId, HttpHeaders headers) {
        return restTemplate.exchange(
                alpacaApiUrl + "/v1/trading/accounts/" + accountId + "/orders/" + orderId,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Map.class
        ).getBody();
    }

    private Map<String, Object> getBuyingPower(String accountId, HttpHeaders headers){
        return restTemplate.exchange(
                alpacaApiUrl + "/v1/trading/accounts/" + accountId+"/account",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Map.class
        ).getBody();
    }

    private Map<String, Object> makeTransferOutgoing(String accountId, HttpHeaders headers, String bankRelationshipId, float platformCommission, String orderId) {
        Map<String, Object> transferBody = new HashMap<>();
        transferBody.put("transfer_type", "ach");
        transferBody.put("relationship_id", bankRelationshipId);
        transferBody.put("amount", platformCommission);
        transferBody.put("direction", "OUTGOING");
        transferBody.put("timing", "immediate");
       // transferBody.put("additional_information", "Pay per comissions");
        //transferBody.put("fee_payment_method", orderId);

        HttpEntity<Map<String, Object>> transferEntity = new HttpEntity<>(transferBody, headers);

        return restTemplate.postForEntity(
                alpacaApiUrl + "/v1/accounts/" + accountId+ "/transfers",
                transferEntity,
                Map.class
        ).getBody();
    }


    private Order saveOrderInDatabase(OrderRequestDTO request, String alpacaOrderId, String alpacaStatus, float filledPrice, float finalAmountPaid, float platformCommission, Float brokerCommission) {
        StockDTO stock = request.getStock();
        int quantity = request.getQuantity();

        Order order = new Order();
        order.setSentToAlpaca(true);
        order.setAlpacaStatus(alpacaStatus);
        order.setFilled_price(filledPrice);
        order.setAlpacaOrderId(alpacaOrderId);
        order.setUserId(request.getUser().getUserId());
        order.setOrderType(request.getOrderType());
        order.setSymbol(stock.getSymbol());
        order.setQuantity(quantity);
        order.setTimeInForce(request.getTimeInForce());
        order.setLimitPrice(request.getLimitPrice());
        order.setStopPrice(request.getStopPrice());
        order.setTotalAmountPaid(finalAmountPaid);
        order.setPlatformCommission(platformCommission);
        order.setBrokerCommission(brokerCommission);
        order.setOrderStatus(OrderStatus.SENDED);
        order.setRequiresSignature(false);
        order.setCreatedAt(LocalDateTime.now());
        order.setSentToAlpacaAt(LocalDateTime.now());
        order.setSignedBy(null);

        return orderRepository.save(order);
    }




}


