package com.example.worldcinema.network.models;

import com.google.gson.annotations.SerializedName;

public class PostUserTokenBody {
    @SerializedName("token")
    private String token;

    public PostUserTokenBody(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


