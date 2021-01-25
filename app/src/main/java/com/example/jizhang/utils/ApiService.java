package com.example.jizhang.utils;

import com.example.jizhang.bean.AddEntryBean;
import com.example.jizhang.bean.DataBean;
import com.example.jizhang.bean.EntriesBean;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {

//    aide18ea5b3-fcec-4c96-a118-966687edaaee

    //查询日期
    @GET("entries")
    Observable<List<EntriesBean>> getEntries(@QueryMap Map<String,Object> map);

    //查询类别
    @GET("categories_pie")
    Observable<AddEntryBean> getCategoriesPie(@QueryMap Map<String,Object> map);

    //查询月报表
    @GET("monthly_trend")
    Observable<AddEntryBean> getMonthlyTrend(@QueryMap Map<String,Object> map);


    //添加单条消费事件
    @POST("add_entry")
    @FormUrlEncoded
    Observable<AddEntryBean> getAddEntry(@FieldMap Map<String,Object> queryMap);

    //添加类别
    @POST("add_category")
    @FormUrlEncoded
    Observable<AddEntryBean> getAddCategory(@PartMap Map<String,Object> queryMap);

    //修改类别
    @PUT("update_category")
    Observable<AddEntryBean> getUpdateCategory(@PartMap Map<String,Object> queryMap);

    //删除类别
    @PUT("delete_category")
    Observable<AddEntryBean> getDeleteCategory(@PartMap Map<String,Object> queryMap);

}
