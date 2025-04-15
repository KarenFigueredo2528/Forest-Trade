package com.unbosque.edu.co.forest.model.dto;

import com.unbosque.edu.co.forest.model.enums.OrderStatus;
import com.unbosque.edu.co.forest.model.enums.OrderType;
import com.unbosque.edu.co.forest.model.enums.TimeInForceOrder;

import java.time.LocalDateTime;

public class OrderDTO {

    private Integer orderId;
    private String alpacaOrderId;
    private Long userId;
    private OrderType orderType;
    private String symbol;
    private Integer quantity;
    private TimeInForceOrder timeInForce;
    private float limitPrice;
    private float stopPrice;
    private float filledPrice;
    private float totalAmountPaid;
    private float pricePerStock;
    private float platformCommission;
    private float brokerCommission;
    private OrderStatus orderStatus;
    private Boolean requiresSignature;
    private LocalDateTime createdAt;
    private LocalDateTime sentToAlpacaAt;
    private Integer signedBy;

    public OrderDTO() {}

    public OrderDTO(Integer orderId, String alpacaOrderId, Long userId, OrderType orderType, String symbol,
                    Integer quantity, TimeInForceOrder timeInForce, float limitPrice, float stopPrice,
                    float filledPrice, float totalAmountPaid, float pricePerStock,
                    float platformCommission, float brokerCommission,
                    OrderStatus orderStatus, Boolean requiresSignature,
                    LocalDateTime createdAt, LocalDateTime sentToAlpacaAt, Integer signedBy) {
        this.orderId = orderId;
        this.alpacaOrderId = alpacaOrderId;
        this.userId = userId;
        this.orderType = orderType;
        this.symbol = symbol;
        this.quantity = quantity;
        this.timeInForce = timeInForce;
        this.limitPrice = limitPrice;
        this.stopPrice = stopPrice;
        this.filledPrice = filledPrice;
        this.totalAmountPaid = totalAmountPaid;
        this.pricePerStock = pricePerStock;
        this.platformCommission = platformCommission;
        this.brokerCommission = brokerCommission;
        this.orderStatus = orderStatus;
        this.requiresSignature = requiresSignature;
        this.createdAt = createdAt;
        this.sentToAlpacaAt = sentToAlpacaAt;
        this.signedBy = signedBy;
    }

    // Getters y Setters

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getAlpacaOrderId() {
        return alpacaOrderId;
    }

    public void setAlpacaOrderId(String alpacaOrderId) {
        this.alpacaOrderId = alpacaOrderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public float getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(float limitPrice) {
        this.limitPrice = limitPrice;
    }

    public float getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(float stopPrice) {
        this.stopPrice = stopPrice;
    }

    public float getFilledPrice() {
        return filledPrice;
    }

    public void setFilledPrice(float filledPrice) {
        this.filledPrice = filledPrice;
    }

    public float getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(float totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public float getPricePerStock() {
        return pricePerStock;
    }

    public void setPricePerStock(float pricePerStock) {
        this.pricePerStock = pricePerStock;
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
}
