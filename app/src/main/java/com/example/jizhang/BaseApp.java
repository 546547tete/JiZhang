package com.example.jizhang;

import android.app.Application;

public class BaseApp extends Application {

    private static BaseApp getApp;
    @Override
    public void onCreate() {
        super.onCreate();
        getApp=this;
    }

    public static BaseApp getGetApp() {
        return getApp;
    }
}
