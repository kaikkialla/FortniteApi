package com.example.tiget.fortniteapi.service;

import com.example.tiget.fortniteapi.model.ShopResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FortniteService {



    @GET("https://fortnite-public-api.theapinetwork.com/prod09/store/get")
    Call<ShopResult> getShop(@Query("language") String address);
/*
    @GET("http://api.etherscan.io/api?module=account&action=txlist&startblock=0&endblock=99999999&sort=asc&apikey=" + API_KEY)
    Call<TxListResult> getId(@Query("address") String address);
  */
/*
    @GET("http://api.etherscan.io/api?module=account&action=txlist&startblock=0&endblock=99999999&sort=asc&apikey=" + API_KEY)
    Call<TxListResult> getStats(@Query("address") String address);
    */
}
