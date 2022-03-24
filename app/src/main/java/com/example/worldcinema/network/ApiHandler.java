package com.example.worldcinema.network;

import com.example.worldcinema.network.service.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class ApiHandler {
    private static ApiHandler mInstance;

    private static final String BASE_URL = "http://cinema.areas.su/auth/";

    private Retrofit retrofit;

    public ApiHandler(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ErrorUtils.retrofit = retrofit;
    }

    public static ApiHandler getInstance() {
        if (mInstance == null) {
            mInstance = new ApiHandler();
        }
        return mInstance;
    }

    public ApiService getService() {
        return retrofit.create(ApiService.class);
    }
}
