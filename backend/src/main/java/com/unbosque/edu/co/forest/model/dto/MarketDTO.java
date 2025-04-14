package com.unbosque.edu.co.forest.model.dto;

import java.time.LocalTime;
import java.util.List;

public class MarketDTO {

    private Integer id;
    private String marketCode;
    private LocalTime openTime;
    private LocalTime closeTime;
    private List<String> businessDays;
    private Integer editedByUserId;

    public MarketDTO() {
    }

    public MarketDTO(Integer id, String marketCode, LocalTime openTime, LocalTime closeTime, List<String> businessDays, Integer editedByUserId) {
        this.id = id;
        this.marketCode = marketCode;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.businessDays = businessDays;
        this.editedByUserId = editedByUserId;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public List<String> getBusinessDays() {
        return businessDays;
    }

    public void setBusinessDays(List<String> businessDays) {
        this.businessDays = businessDays;
    }

    public Integer getEditedByUserId() {
        return editedByUserId;
    }

    public void setEditedByUserId(Integer editedByUserId) {
        this.editedByUserId = editedByUserId;
    }
}

