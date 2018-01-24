package com.itsblockchain.itsblockchain.DataProviders;

/**
 * Created by anonymous on 24/1/18.
 */

public class CoinData {

    String imgUrl, name;
    Double value, change;

    public CoinData(String imgUrl, String name, Double value, Double change) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.value = value;
        this.change = change;

    }


    public Double getChange() {
        return change;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }
}
