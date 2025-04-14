package com.unbosque.edu.co.forest.model.dto;

public class AssetDTO {

    private String symbol;
    private String name;
    private String status;
    private String exchange;

    public AssetDTO() {}

    public AssetDTO(String symbol, String name, String status, String exchange) {
        this.symbol = symbol;
        this.name = name;
        this.status = status;
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
