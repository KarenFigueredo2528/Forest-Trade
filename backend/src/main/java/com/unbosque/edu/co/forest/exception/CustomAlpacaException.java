package com.unbosque.edu.co.forest.exception;

public class CustomAlpacaException extends RuntimeException {

    private final int statusCode;
    private final String alpacaMessage;

    public CustomAlpacaException(int statusCode, String alpacaMessage) {
        super("Alpaca API error [" + statusCode + "]: " + alpacaMessage);
        this.statusCode = statusCode;
        this.alpacaMessage = alpacaMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getAlpacaMessage() {
        return alpacaMessage;
    }
}
