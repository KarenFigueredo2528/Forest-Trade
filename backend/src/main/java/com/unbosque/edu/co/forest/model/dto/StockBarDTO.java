package com.unbosque.edu.co.forest.model.dto;

public class StockBarDTO {

    private float closePrice;
    private int volume;

    public StockBarDTO() {}

    public StockBarDTO(float closePrice, int volume) {
        this.closePrice = closePrice;
        this.volume = volume;
    }

    public float getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(float closePrice) {
        this.closePrice = closePrice;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
