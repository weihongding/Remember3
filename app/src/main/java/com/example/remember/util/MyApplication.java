package com.example.remember.util;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

//全局中获取当前context
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        LitePal.initialize(context);
        super.onCreate();
    }

    public static Context getContext(){
        return context;
    }


}
