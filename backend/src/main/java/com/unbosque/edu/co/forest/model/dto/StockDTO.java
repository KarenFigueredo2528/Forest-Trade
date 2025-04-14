package com.unbosque.edu.co.forest.model.dto;

import com.unbosque.edu.co.forest.model.entity.Market;

public class StockDTO {

    private String symbol;
    private String stockName;
    private String sector;
    private float currentPrice;
    private float volume;
    private float marketCapitalization;
    private String status;
    private Market market;

    public StockDTO() {}

    public StockDTO(String symbol, String stockName, String sector, float currentPrice, float volume, float marketCapitalization, String status, Market market) {
        this.symbol = symbol;
        this.stockName = stockName;
        this.sector = sector;
        this.currentPrice = currentPrice;
        this.volume = volume;
        this.marketCapitalization = marketCapitalization;
        this.status = status;
        this.market = market;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getMarketCapitalization() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(float marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
}
