package com.unbosque.edu.co.forest.model.dto;

import java.util.Map;

public class StockBarResponse {

    private Map<String, StockBarDTO> bars;

    public StockBarResponse() {
    }

    public Map<String, StockBarDTO> getBars() {
        return bars;
    }

    public void setBars(Map<String, StockBarDTO> bars) {
        this.bars = bars;
    }

}
