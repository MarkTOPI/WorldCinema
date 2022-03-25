package com.example.worldcinema.network.service;

import com.example.worldcinema.network.models.LoginBody;
import com.example.worldcinema.network.models.MovieResponse;
import com.example.worldcinema.network.models.RegisterBody;
import com.example.worldcinema.network.models.LoginResponse;
import com.example.worldcinema.network.models.RegistrationResponse;
import com.example.worldcinema.network.models.UserInfoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> goFeed(@Body LoginBody registerBody);

    @POST("register")
    Call<RegistrationResponse> goRegistration(@Body RegisterBody registerBody);

    @GET("movies?filter=inTrend")
    Call<List<MovieResponse>> getMovies();
}
