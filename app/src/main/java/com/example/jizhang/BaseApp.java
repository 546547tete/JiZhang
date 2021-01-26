package com.example.jizhang;

import android.app.Application;

import com.example.httplibrary.HttpConstant;
import com.example.httplibrary.HttpGlobalConfig;

public class BaseApp extends Application {

    private static BaseApp getApp;
    String BASEURL="http://192.168.31.13:8001/";
    @Override
    public void onCreate() {
        super.onCreate();
        getApp=this;


        HttpGlobalConfig.getInsance()
                .setBaseUrl(BASEURL)
//                .setInterceptors(interceptors)
                .setTimeout(HttpConstant.TIME_OUT)
//                .setAppKey(Constans.JPUSHREGISTID,JPushInterface.getRegistrationID(this))
                .setShowLog(true)
                .setTimeUnit(HttpConstant.TIME_UNIT)
                .initReady(this);
    }

    public static BaseApp getGetApp() {
        return getApp;
    }
}
