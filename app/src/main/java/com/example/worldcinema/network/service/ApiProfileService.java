package com.example.worldcinema.network.service;

import com.example.worldcinema.network.models.UserInfoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ApiProfileService {
    @GET("user")
    Call<List<UserInfoResponse>> getData(@Header("Authorization") String token);
}
