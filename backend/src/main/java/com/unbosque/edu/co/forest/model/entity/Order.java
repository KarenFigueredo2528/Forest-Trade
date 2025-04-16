package com.unbosque.edu.co.forest.model.entity;

import com.unbosque.edu.co.forest.model.enums.OrderStatus;
import com.unbosque.edu.co.forest.model.enums.OrderType;
import com.unbosque.edu.co.forest.model.enums.TimeInForceOrder;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(name = "alpaca_id", unique = true)
    private String alpacaOrderId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false)
    private OrderType orderType;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_in_force", nullable = false)
    private TimeInForceOrder timeInForce;

    @Column(name = "limit_price")
    private Float limitPrice;

    @Column(name = "stop_price")
    private Float stopPrice;

    @Column(name = "filled_price")
    private float filled_price; //Total por acciones

    @Column(name = "total_amount_paid")
    private float totalAmountPaid; // Precio total pagado

    @Column(name = "platform_commission")
    private float platformCommission; // comision de la plataforma

    @Column(name = "broker_commission")
    private float brokerCommission; //comision del broker

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "requires_signature")
    private Boolean requiresSignature;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "sent_to_alpaca_at")
    private LocalDateTime sentToAlpacaAt;

    @Column(name = "signed_by")
    private Integer signedBy;

    @Column(name = "sent_to_alpaca")
    private boolean sentToAlpaca;

    @Column(name = "alpaca_sended_status")
    private String alpacaStatus;


    public Order(Integer orderId, String alpacaOrderId, Integer userId, OrderType orderType, String symbol, Integer quantity, TimeInForceOrder timeInForce, Float limitPrice, Float stopPrice, float filled_price, float totalAmountPaid, float platformCommission, float brokerCommission, OrderStatus orderStatus, Boolean requiresSignature, LocalDateTime createdAt, LocalDateTime sentToAlpacaAt, Integer signedBy, boolean sentToAlpaca, String alpacaStatus) {
        this.orderId = orderId;
        this.alpacaOrderId = alpacaOrderId;
        this.userId = userId;
        this.orderType = orderType;
        this.symbol = symbol;
        this.quantity = quantity;
        this.timeInForce = timeInForce;
        this.limitPrice = limitPrice;
        this.stopPrice = stopPrice;
        this.filled_price = filled_price;
        this.totalAmountPaid = totalAmountPaid;
        this.platformCommission = platformCommission;
        this.brokerCommission = brokerCommission;
        this.orderStatus = orderStatus;
        this.requiresSignature = requiresSignature;
        this.createdAt = createdAt;
        this.sentToAlpacaAt = sentToAlpacaAt;
        this.signedBy = signedBy;
        this.sentToAlpaca = sentToAlpaca;
        this.alpacaStatus = alpacaStatus;
    }

    public Order() {
    }

    public boolean isSentToAlpaca() {
        return sentToAlpaca;
    }

    public void setSentToAlpaca(boolean sentToAlpaca) {
        this.sentToAlpaca = sentToAlpaca;
    }

    public String getAlpacaOrderId() {
        return alpacaOrderId;
    }

    public void setAlpacaOrderId(String alpacaOrderId) {
        this.alpacaOrderId = alpacaOrderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public TimeInForceOrder getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(TimeInForceOrder timeInForce) {
        this.timeInForce = timeInForce;
    }

    public Float getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(Float limitPrice) {
        this.limitPrice = limitPrice;
    }

    public Float getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(Float stopPrice) {
        this.stopPrice = stopPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public float getFilled_price() {
        return filled_price;
    }

    public void setFilled_price(float filled_price) {
        this.filled_price = filled_price;
    }

    public float getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(float totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public float getPlatformCommission() {
        return platformCommission;
    }

    public void setPlatformCommission(float platformCommission) {
        this.platformCommission = platformCommission;
    }

    public float getBrokerCommission() {
        return brokerCommission;
    }

    public void setBrokerCommission(float brokerCommission) {
        this.brokerCommission = brokerCommission;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getRequiresSignature() {
        return requiresSignature;
    }

    public void setRequiresSignature(Boolean requiresSignature) {
        this.requiresSignature = requiresSignature;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getSentToAlpacaAt() {
        return sentToAlpacaAt;
    }

    public void setSentToAlpacaAt(LocalDateTime sentToAlpacaAt) {
        this.sentToAlpacaAt = sentToAlpacaAt;
    }

    public Integer getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(Integer signedBy) {
        this.signedBy = signedBy;
    }

    public String getAlpacaStatus() {
        return alpacaStatus;
    }

    public void setAlpacaStatus(String alpacaStatus) {
        this.alpacaStatus = alpacaStatus;
    }
}
