package com.example.tiget.fortniteapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("cost")
    @Expose
    int price;

    @SerializedName("rarity")
    @Expose
    String rarity;

    @SerializedName("type")
    @Expose
    String type;

    @SerializedName("image")
    @Expose
    String image;
}