package com.example.jizhang.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static volatile ApiService ourInstance;

    public static ApiService getInstance() {
        if (ourInstance==null){
            synchronized (RetrofitUtils.class){
                if (ourInstance==null){
                    ourInstance = new RetrofitUtils().getRetrofit();
                }
            }
        }
        return ourInstance;
    }

    private ApiService getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.31.13:8001/")
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    private RetrofitUtils() {
    }
}
