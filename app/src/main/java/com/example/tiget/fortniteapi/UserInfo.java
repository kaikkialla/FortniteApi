package com.example.tiget.fortniteapi;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserInfo implements Serializable {

    public String id;
    public List<String> platforms = new ArrayList<>();

    public UserInfo(String id, List<String> platforms){
        this.id = id;
        this.platforms = platforms;
    }
}
