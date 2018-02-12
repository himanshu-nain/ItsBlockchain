package com.itsblockchain.itsblockchain.DataProviders;

/**
 * Created by anonymous on 24/1/18.
 */

public class PortfolioCoinData {

    String id, name, symbol, buy_price, buy_quantity, buy_amount, last_change;

    public PortfolioCoinData() {
    }

    public PortfolioCoinData(String id, String name, String symbol, String buy_price, String buy_quantity, String buy_amount) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.buy_price = buy_price;
        this.buy_quantity = buy_quantity;
        this.buy_amount = buy_amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(String buy_price) {
        this.buy_price = buy_price;
    }

    public String getBuy_quantity() {
        return buy_quantity;
    }

    public void setBuy_quantity(String buy_quantity) {
        this.buy_quantity = buy_quantity;
    }

    public String getBuy_amount() {
        return buy_amount;
    }

    public void setBuy_amount(String buy_amount) {
        this.buy_amount = buy_amount;
    }

    public String getLast_change() {
        return last_change;
    }

    public void setLast_change(String last_change) {
        this.last_change = last_change;
    }
}
