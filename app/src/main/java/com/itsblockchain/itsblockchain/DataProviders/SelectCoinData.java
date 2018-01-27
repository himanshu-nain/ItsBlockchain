package com.itsblockchain.itsblockchain.DataProviders;

/**
 * Created by anonymous on 27/1/18.
 */

public class SelectCoinData {

    String name, symbol;

    public SelectCoinData(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getFull(){
        return name +' '+ symbol;
    }
}
