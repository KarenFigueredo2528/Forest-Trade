package com.unbosque.edu.co.forest.model.dto;

import com.unbosque.edu.co.forest.model.enums.OrderType;
import com.unbosque.edu.co.forest.model.enums.TimeInForceOrder;

public class OrderRequestDTO {

    private UserSessionDTO user;
    private OrderType orderType;
    private Integer quantity;
    private TimeInForceOrder timeInForce;
    private Float limitPrice;
    private Float stopPrice;
    private boolean requiresSignature;
    private StockDTO stock;

    public UserSessionDTO getUser() {
        return user;
    }

    public void setUser(UserSessionDTO user) {
        this.user = user;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
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

    public boolean isRequiresSignature() {
        return requiresSignature;
    }

    public void setRequiresSignature(boolean requiresSignature) {
        this.requiresSignature = requiresSignature;
    }

    public StockDTO getStock() {
        return stock;
    }

    public void setStock(StockDTO stock) {
        this.stock = stock;
    }
}
