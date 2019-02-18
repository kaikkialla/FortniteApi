package com.example.tiget.fortniteapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopResult {
    @Expose
    public List<Item> items;
}