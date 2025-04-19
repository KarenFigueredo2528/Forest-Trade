package com.unbosque.edu.co.forest.model.dto;

import com.unbosque.edu.co.forest.model.enums.OrderStatus;
import com.unbosque.edu.co.forest.model.enums.OrderType;
import com.unbosque.edu.co.forest.model.enums.Role;
import com.unbosque.edu.co.forest.model.enums.TimeInForceOrder;

import java.time.LocalDateTime;

public class OrderDTO {

    private Integer orderId;
    private String alpacaOrderId;
    private Integer userId;
    private OrderType orderType;
    private String symbol;
    private Integer quantity;
    private TimeInForceOrder timeInForce;
    private Float limitPrice;
    private Float stopPrice;
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
    private String alpacaStatus;
    private boolean sentToAlpaca;
    private Role initiatedBy;
    private Integer stockbrokerId;

    public OrderDTO() {}

    public OrderDTO(Integer orderId, String alpacaOrderId, Integer userId, OrderType orderType, String symbol, Integer quantity, TimeInForceOrder timeInForce, Float limitPrice, Float stopPrice, float filledPrice, float totalAmountPaid, float pricePerStock, float platformCommission, float brokerCommission, OrderStatus orderStatus, Boolean requiresSignature, LocalDateTime createdAt, LocalDateTime sentToAlpacaAt, Integer signedBy, String alpacaStatus, boolean sentToAlpaca, Role initiatedBy, Integer stockbrokerId) {
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
        this.alpacaStatus = alpacaStatus;
        this.sentToAlpaca = sentToAlpaca;
        this.initiatedBy = initiatedBy;
        this.stockbrokerId = stockbrokerId;
    }

    public Integer getStockbrokerId() {
        return stockbrokerId;
    }

    public void setStockbrokerId(Integer stockbrokerId) {
        this.stockbrokerId = stockbrokerId;
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

    public String getAlpacaStatus() {
        return alpacaStatus;
    }

    public void setAlpacaStatus(String alpacaStatus) {
        this.alpacaStatus = alpacaStatus;
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
