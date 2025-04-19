package com.unbosque.edu.co.forest.service;

import com.unbosque.edu.co.forest.exception.CustomAlpacaException;
import com.unbosque.edu.co.forest.model.dto.OrderRequestDTO;
import com.unbosque.edu.co.forest.model.dto.StockDTO;
import com.unbosque.edu.co.forest.model.dto.UserSessionDTO;
import com.unbosque.edu.co.forest.model.entity.Order;
import com.unbosque.edu.co.forest.model.entity.Transaction;
import com.unbosque.edu.co.forest.model.enums.*;
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
import java.util.Optional;

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

    private final UserService userService;
    private final TransactionService transactionService;

    public OrderService(@Qualifier("brokerRestTemplate") RestTemplate restTemplate, OrderRepository orderRepository, UserService userService, TransactionService transactionService) {
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    public Order createBuyOrder(OrderRequestDTO request) {

        StockDTO stock = request.getStock();
        UserSessionDTO user = request.getUser();

        float pricePerStock = stock.getCurrentPrice();
        int quantity = request.getQuantity();
        float total = pricePerStock * quantity;
        float platformCommission = Math.round(total * commissionPercentage * 100.0f) / 100.0f;
        float totalWithCommission  = total + platformCommission;
        float stockbrokerCommission = 0f;
        OrderStatus status = OrderStatus.SENDED;
        Integer stockbrokerId = null;
        Role initiatedBy = Role.INVESTOR;
        TransactionStatus transactionStatus = TransactionStatus.PENDING;


        // Validar balance disponible
        userService.validateBalance(user.getUserId(), totalWithCommission );

        // Validar si requiere firma (Lo envio un usuario y debe aprobar comisionista)
        if (Boolean.TRUE.equals(request.isRequiresSignature())) {
           // definir aqui stockbroker comision
            return savePendingApprovalOrder(request, OrderStatus.PENDING_BROKER, initiatedBy, null, total, totalWithCommission, platformCommission,stockbrokerCommission);

        } //valida si es enviada por comisionista
        else if(request.getBrokerId() != null){
            //definir aqui comision
            return savePendingApprovalOrder(request, OrderStatus.PENDING_INVESTOR, Role.STOCKBROKER, request.getBrokerId(), total, totalWithCommission, platformCommission,stockbrokerCommission);
        }
        // Si no requiere ninguna aprobacion
        else {

            Map<String, Object> bodyOrder = buildAlpacaOrderBody(request);
            HttpHeaders headers = buildAlpacaHeaders();

            try {

                // Enviar orden a Alpaca

                Map<String, Object> responseBodyOrder = sendOrderToAlpaca(user.getAlpacaAccountId(), new HttpEntity<>(bodyOrder, headers));
                String alpacaOrderId = (String) responseBodyOrder.get("id");

                //Obtener ultimo estado de la orden
                Map<String, Object> updatedOrder = getAlpacaOrderStatus(user.getAlpacaAccountId(), alpacaOrderId, headers);
                String updatedStatus = (String) updatedOrder.get("status");

                // Si se ejecuto al instante, se generan transacciones y descuento de saldo

                if ("filled".equalsIgnoreCase(updatedStatus)) {
                    float filledPricePerStock = Optional.ofNullable(updatedOrder.get("filled_avg_price"))
                            .map(Object::toString)
                            .map(Float::parseFloat)
                            .orElse(pricePerStock);

                    total = filledPricePerStock * quantity;
                    platformCommission = Math.round(total * commissionPercentage * 100.0f) / 100.0f;
                    totalWithCommission = total + platformCommission;
                    transactionStatus = TransactionStatus.CONFIRMED;

                }

                userService.subtractFromBalance(user.getUserId(), totalWithCommission);

                Order orderSaved = buildAndSaveOrder(
                        true,
                        LocalDateTime.now(),
                        status,
                        request,
                        alpacaOrderId,
                        updatedStatus.toUpperCase(),
                        total,
                        totalWithCommission ,
                        platformCommission,
                        0f, // aún no calculamos comisión comisionista
                        initiatedBy,
                        stockbrokerId
                );

                handleTransactions(orderSaved, total, platformCommission, transactionStatus, stock.getSymbol(), quantity);


                // Guardar orden no ejecutada al instante
                return orderSaved;

            } catch (HttpClientErrorException | HttpServerErrorException ex) {
                String responseMessage = ex.getResponseBodyAsString();
                throw new CustomAlpacaException(ex.getRawStatusCode(), responseMessage);
            } catch (RestClientException ex) {
                throw new CustomAlpacaException(500, "Error al conectar con Alpaca: " + ex.getMessage());
            }
        }


    }




    private Map<String, Object> buildAlpacaOrderBody(OrderRequestDTO request) {
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


    private Order buildAndSaveOrder(boolean sendToAlpaca, LocalDateTime sendToAlpacaAt, OrderStatus orderStatus, OrderRequestDTO request, String alpacaOrderId, String alpacaStatus, float filledPrice, float finalAmountPaid, float platformCommission, Float brokerCommission, Role initiatedBy, Integer stockbrokerId) {
            StockDTO stock = request.getStock();
            int quantity = request.getQuantity();

            Order order = new Order(null, request.getUser().getUserId(), stock.getSymbol(), quantity, request.getOrderType(), alpacaOrderId, orderStatus, request.getLimitPrice(), request.isRequiresSignature(), null, LocalDateTime.now(),  sendToAlpacaAt, sendToAlpaca, filledPrice, request.getTimeInForce(), request.getStopPrice(), platformCommission, brokerCommission, finalAmountPaid, alpacaStatus, initiatedBy, stockbrokerId );
            return orderRepository.save(order);
    }

    private void handleTransactions(Order order, float total, float platformCommission, TransactionStatus status, String symbol, int quantity) {

        transactionService.createTransaction(new Transaction(
                null, order.getUserId(), order.getOrderId(), -total,
                TransactionType.BUY, "Compra de " + quantity + " acciones de " + symbol,
                LocalDateTime.now(), status
        ));

        transactionService.createTransaction(new Transaction(
                null, order.getUserId(), order.getOrderId(), -platformCommission,
                TransactionType.COMMISSION_FT, "Comisión de la plataforma por compra de acciones",
                LocalDateTime.now(), status
        ));
    }


    private Order savePendingApprovalOrder(OrderRequestDTO request, OrderStatus status, Role initiatedBy, Integer brokerId,
                                           float total, float totalWithCommission, float platformCommission, float brokerCommission) {
        return buildAndSaveOrder(false, null, status, request, null, null, total, totalWithCommission,
                platformCommission, brokerCommission, initiatedBy, brokerId);
    }





}


