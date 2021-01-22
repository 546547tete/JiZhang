package com.example.jizhang.utils;

import com.example.jizhang.bean.DataBean;
import com.google.gson.JsonElement;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {

    @POST()
    @FormUrlEncoded
    Observable<JsonElement> getDataBean(@Url String url,
                                        @PartMap Map<String,String> queryMap);

}
