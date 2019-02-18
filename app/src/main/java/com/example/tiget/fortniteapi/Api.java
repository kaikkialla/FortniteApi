package com.example.tiget.fortniteapi;

import android.content.Intent;
import android.util.Log;


import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api {

/*
    public static ArrayList<ShopItem> ShopItems = new ArrayList<>();
    //public static int itemsQuantity;
    public static UserInfo userInfo;

    //Магаз- https://fortnite-public-api.theapinetwork.com/prod09/store/get?language={language}
    //id - https://fortnite-public-api.theapinetwork.com/prod09/users/id?username={name}
    //стата - https://fortnite-public-api.theapinetwork.com/prod09/users/public/br_stats?user_id={id}&platform={platform}

    private static final String DailyStoreApi = "https://fortnite-public-api.theapinetwork.com/prod09/store/get";

    private static final String UserStatsApi = "https://fortnite-public-api.theapinetwork.com/prod09/users/public/br_stats?user_id=4735ce9132924caf8a5b17789b40f79c&platform=pc";
    private static final String UserIdApi = "https://fortnite-public-api.theapinetwork.com/prod09/users/id";
    private static final String GameInfoApi = "https://fortnite-public-api.theapinetwork.com/prod09/status/fortnite_server_status";
    private static String language;


    //делаем запрос к апишке и получаем код
    public static void loadStore() {
        final Request request = new Request.Builder().url(DailyStoreApi + "?language=" + "en").build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("AFSPIGIPSG", "Store Connected failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("AFSPIGIPSG", "Store Connected Successful");
                String data = response.body().string();
                try {
                    parseStore(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }




    //получаем информацию из апишки
    public static void parseStore(String data)throws JSONException {

        ShopItems.clear();
        JSONObject rootObject = new JSONObject(data);
        int itemsQuantity = rootObject.getInt("rows");
        JSONArray items = rootObject.getJSONArray("items");

        for (int i = 0; i <= itemsQuantity - 1; i++) {
            JSONObject item = items.getJSONObject(i);
            String name = item.getString("name");
            int price = item.getInt("cost");
            String type = item.getJSONObject("item").getString("type");
            String rarity = item.getJSONObject("item").getString("rarity");
            String image = item.getJSONObject("item").getJSONObject("images").getString("background");

            ShopItem shopItem = new ShopItem(name, price, rarity, type, image);
            ShopItems.add(shopItem);
        }
        EventBus.getDefault().post(new storeStatus(true));


    }








    //делаем запрос к апишке и получаем код
    public static void loadId(String username) {
        final Request request = new Request.Builder().url(UserIdApi + "?username=" + username).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("AFSPIGIPSG", "Id Connected failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("AFSPIGIPSG", "Id Connected Successful");
                String data = response.body().string();
                try {
                    parseId(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }




    //получаем информацию из апишки
    public static void parseId(String username)throws JSONException {
        JSONObject rootObject = new JSONObject(username);
        String id = rootObject.getString("uid");
        List<String> platforms = new ArrayList<>();


        JSONArray items = rootObject.getJSONArray("platforms");
        for (int i = 0; i <= items.length() - 1; i++) {
            String platform = items.get(i).toString();
            platforms.add(platform);
        }

        userInfo = new UserInfo(id, platforms);
        EventBus.getDefault().post(new idStatus(true));

    }





    public static void loadStats(String id, String platform) {
        final Request request = new Request.Builder().url(UserStatsApi + "?user_id=" + id + "&platform=" + platform).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("AFSPIGIPSG", "Id Connected failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("AFSPIGIPSG", "Id Connected Successful");
                String data = response.body().string();
                try {
                    parseStats(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }




    //получаем информацию из апишки
    public static void parseStats(String username)throws JSONException {
        ShopItems.clear();
        JSONObject rootObject = new JSONObject(username);

    }








    public static class storeStatus {
        public storeStatus(boolean loaded) {
            this.loaded = loaded;
        }
        boolean loaded;
    }

    public static class idStatus {
        public idStatus(boolean loaded) {
            this.loaded = loaded;
        }
        boolean loaded;
    }

    public static class statsStatus {
        public statsStatus(boolean loaded) {
            this.loaded = loaded;
        }
        boolean loaded;
    }

    public enum platform{
        PC, PS4, XboxOne,
    }

    public enum language{
        en, de
    }
    */
}