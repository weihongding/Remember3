package com.example.remember.util;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    //阿里云服务器地址：http://47.100.198.114/api
    //tomcat服务器地址：http://192.168.43.148:8080/api
    //IDEA服务器地址：http://192.168.43.148:8080
    public static String urlHead = "http://47.100.198.114/api";

    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        new LogdUtil(address);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(5, TimeUnit.SECONDS)//设置读取超时时间;
                .build();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttpRequest(String address, RequestBody body, okhttp3.Callback callback){
        new LogdUtil(address);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(5, TimeUnit.SECONDS)//设置读取超时时间;
                .build();
        Request request = new Request.Builder().url(address).post(body).build();
        client.newCall(request).enqueue(callback);
    }

}
