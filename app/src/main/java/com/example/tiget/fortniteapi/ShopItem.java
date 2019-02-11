package com.example.tiget.fortniteapi;

import java.io.Serializable;

public class ShopItem implements Serializable {

    public String name;
    public int price;
    public String rarity;
    public String type;
    public String image;

    public ShopItem(String name, int price, String rarity, String type, String image){
        this.name = name;
        this.price = price;
        this.rarity = rarity;
        this.type = type;
        this.image = image;
    }
}
