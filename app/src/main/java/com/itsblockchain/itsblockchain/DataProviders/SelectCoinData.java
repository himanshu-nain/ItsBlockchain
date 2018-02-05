package com.itsblockchain.itsblockchain.DataProviders;

/**
 * Created by anonymous on 27/1/18.
 */

public class SelectCoinData {

    String id, name, symbol;

    public SelectCoinData(String id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
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
