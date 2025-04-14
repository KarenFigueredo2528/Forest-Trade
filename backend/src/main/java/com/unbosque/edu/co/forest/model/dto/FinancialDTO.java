package com.unbosque.edu.co.forest.model.dto;
public class FinancialDTO {

    private String sector;
    private float marketCap;

    public FinancialDTO() {}

    public FinancialDTO(String sector, float marketCap) {
        this.sector = sector;
        this.marketCap = marketCap;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public float getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(float marketCap) {
        this.marketCap = marketCap;
    }
}
