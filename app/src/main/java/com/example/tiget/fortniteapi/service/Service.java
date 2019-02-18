package com.example.tiget.fortniteapi.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    static FortniteService fortniteService;

    public static FortniteService getInstance() {
        if (fortniteService == null) {
            final Gson gson = new GsonBuilder().setLenient().create();
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://fortnite-public-api.theapinetwork.com/prod09/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            fortniteService = retrofit.create(FortniteService.class);
        }
        return fortniteService;
    }



}