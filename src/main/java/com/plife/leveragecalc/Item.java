package com.plife.leveragecalc;

public class Item {
    private String symbol;
    private String price;
    private String indexPrice;
    private String markPrice;
    private String timestamp;

    // Constructor
    public Item(String symbol, String price, String indexPrice, String markPrice, String timestamp) {
        this.symbol = symbol;
        this.price = price;
        this.indexPrice = indexPrice;
        this.markPrice = markPrice;
        this.timestamp = timestamp;
    }

    // Getters
    public String getSymbol() {
        return symbol;
    }

    public String getPrice() {
        return price;
    }

    public String getIndexPrice() {
        return indexPrice;
    }

    public String getMarkPrice() {
        return markPrice;
    }

    public String getTimestamp() {
        return timestamp;
    }

    // Setters (if needed)
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setIndexPrice(String indexPrice) {
        this.indexPrice = indexPrice;
    }

    public void setMarkPrice(String markPrice) {
        this.markPrice = markPrice;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // Override toString() for debugging
    @Override
    public String toString() {
        return "Item{" +
                "symbol='" + symbol + '\'' +
                ", price='" + price + '\'' +
                ", indexPrice='" + indexPrice + '\'' +
                ", markPrice='" + markPrice + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
