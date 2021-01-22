package com.example.httplibrary.utils;

import android.os.Looper;

public class ThreadUtils {

    //是否是主线程
    public static boolean isMainThread(){
        return Looper.getMainLooper().getThread().getId()== Thread.currentThread().getId();
    }
}
