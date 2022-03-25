package com.example.worldcinema.network;

import com.example.worldcinema.network.service.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieHandler {
    private static MovieHandler mInstance;
    private static final String BASEURL = "http://cinema.areas.su/";
    private Retrofit retrofit;

    public MovieHandler() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ErrorUtils.retrofit = retrofit;
    }

    public static MovieHandler getInstance() {
        if (mInstance == null) {
            mInstance = new MovieHandler();
        }

        return mInstance;
    }

    public ApiService getService() {
        return retrofit.create(ApiService.class);
    }
}
