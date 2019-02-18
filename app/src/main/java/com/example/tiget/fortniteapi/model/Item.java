package com.example.tiget.fortniteapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("image")
    @Expose
    public String image;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("cost")
    @Expose
    public int price;
/*
    @SerializedName("rarity")
    @Expose
    public String rarity;

    @SerializedName("type")
    @Expose
    public String type;
*/

}