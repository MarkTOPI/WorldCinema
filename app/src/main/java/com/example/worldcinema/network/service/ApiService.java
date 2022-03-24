package com.example.worldcinema.network.service;

import com.example.worldcinema.network.models.LoginBody;
import com.example.worldcinema.network.models.RegisterBody;
import com.example.worldcinema.network.models.LoginResponse;
import com.example.worldcinema.network.models.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> goFeed(@Body LoginBody registerBody);

    @POST("register")
    Call<RegistrationResponse> goRegistration(@Body RegisterBody registerBody);
}
