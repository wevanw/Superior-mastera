package com.example.lobster.superior.model;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class Goods extends DataSupport implements Serializable {//商品
    private int id;
    private String name;
    private String number;
    private String price;
    private String marketName;
    private byte[] markeImage;

    public Goods(String name, String number, String price, String marketName, byte[] markeImage) {
        this.name = name;
        this.number = number;
        this.price = price;

        this.marketName = marketName;
        this.markeImage = markeImage;
    }

    public Goods(String name, String number, String price, String marketName) {
        this.name = name;
        this.number = number;
        this.price = price;
        this.marketName = marketName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public byte[] getMarkeImage() {
        return markeImage;
    }

    public void setMarkeImage(byte[] markeImage) {
        this.markeImage = markeImage;
    }


}
