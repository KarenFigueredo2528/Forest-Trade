package com.unbosque.edu.co.forest.model.dto;

import com.unbosque.edu.co.forest.model.enums.OrderType;

public class UserSessionDTO {

    private Integer userId;
    private String alpacaAccountId;
    private String bankRelationshipId;
    private String roleName;
    private Integer StockBrokerId;
    private Integer dailyOrderLimit;
    private String defaultOrderType;

    public UserSessionDTO(Integer userId, String alpacaAccountId, String bankRelationshipId, String roleName, Integer stockBrokerId, Integer dailyOrderLimit, String defaultOrderType) {
        this.userId = userId;
        this.alpacaAccountId = alpacaAccountId;
        this.bankRelationshipId = bankRelationshipId;
        this.roleName = roleName;
        StockBrokerId = stockBrokerId;
        this.dailyOrderLimit = dailyOrderLimit;
        this.defaultOrderType = defaultOrderType;
    }

    public UserSessionDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAlpacaAccountId() {
        return alpacaAccountId;
    }

    public void setAlpacaAccountId(String alpacaAccountId) {
        this.alpacaAccountId = alpacaAccountId;
    }

    public String getBankRelationshipId() {
        return bankRelationshipId;
    }

    public void setBankRelationshipId(String bankRelationshipId) {
        this.bankRelationshipId = bankRelationshipId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getStockBrokerId() {
        return StockBrokerId;
    }

    public void setStockBrokerId(Integer stockBrokerId) {
        StockBrokerId = stockBrokerId;
    }

    public Integer getDailyOrderLimit() {
        return dailyOrderLimit;
    }

    public void setDailyOrderLimit(Integer dailyOrderLimit) {
        this.dailyOrderLimit = dailyOrderLimit;
    }

    public String getDefaultOrderType() {
        return defaultOrderType;
    }

    public void setDefaultOrderType(String defaultOrderType) {
        this.defaultOrderType = defaultOrderType;
    }
}
