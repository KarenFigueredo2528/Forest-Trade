package com.unbosque.edu.co.forest.model.entity;

import com.unbosque.edu.co.forest.model.enums.OrderStatus;
import com.unbosque.edu.co.forest.model.enums.OrderType;
import com.unbosque.edu.co.forest.model.enums.Role;
import com.unbosque.edu.co.forest.model.enums.TimeInForceOrder;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false)
    private OrderType orderType;

    @Column(name = "alpaca_id", unique = true)
    private String alpacaOrderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "limit_price")
    private Float limitPrice;

    @Column(name = "requires_signature")
    private Boolean requiresSignature;

    @Column(name = "signed_by")
    private Integer signedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "sent_to_alpaca_at")
    private LocalDateTime sentToAlpacaAt;

    @Column(name = "sent_to_alpaca")
    private boolean sentToAlpaca;

    @Column(name = "filled_price")
    private float filled_price; //Total por acciones

    @Enumerated(EnumType.STRING)
    @Column(name = "time_in_force", nullable = false)
    private TimeInForceOrder timeInForce;

    @Column(name = "stop_price")
    private Float stopPrice;

    @Column(name = "platform_commission")
    private float platformCommission; // comision de la plataforma

    @Column(name = "broker_commission")
    private float brokerCommission; //comision del broker

    @Column(name = "total_amount_paid")
    private float totalAmountPaid; // Precio total pagado

    @Column(name = "alpaca_sended_status")
    private String alpacaStatus;

    @Enumerated(EnumType.STRING)
    @Column(name="initiated_by")
    private Role initiatedBy;

    @Column(name = "stockbroker_id")
    private Integer stockbrokerId;


    public Order(Integer orderId, Integer userId, String symbol, Integer quantity, OrderType orderType, String alpacaOrderId, OrderStatus orderStatus, Float limitPrice, Boolean requiresSignature, Integer signedBy, LocalDateTime createdAt, LocalDateTime sentToAlpacaAt, boolean sentToAlpaca, float filled_price, TimeInForceOrder timeInForce, Float stopPrice, float platformCommission, float brokerCommission, float totalAmountPaid, String alpacaStatus, Role initiatedBy, Integer stockbrokerId) {
        this.orderId = orderId;
        this.userId = userId;
        this.symbol = symbol;
        this.quantity = quantity;
        this.orderType = orderType;
        this.alpacaOrderId = alpacaOrderId;
        this.orderStatus = orderStatus;
        this.limitPrice = limitPrice;
        this.requiresSignature = requiresSignature;
        this.signedBy = signedBy;
        this.createdAt = createdAt;
        this.sentToAlpacaAt = sentToAlpacaAt;
        this.sentToAlpaca = sentToAlpaca;
        this.filled_price = filled_price;
        this.timeInForce = timeInForce;
        this.stopPrice = stopPrice;
        this.platformCommission = platformCommission;
        this.brokerCommission = brokerCommission;
        this.totalAmountPaid = totalAmountPaid;
        this.alpacaStatus = alpacaStatus;
        this.initiatedBy = initiatedBy;
        this.stockbrokerId = stockbrokerId;
    }

    public Integer getStockbrokerId() {
        return stockbrokerId;
    }

    public void setStockbrokerId(Integer stockbrokerId) {
        this.stockbrokerId = stockbrokerId;
    }

    public Order() {
    }

    public Role getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(Role initiatedBy) {
        this.initiatedBy = initiatedBy;
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
