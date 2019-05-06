package com.example.remember.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.remember.entity.UserInfo;

import java.util.HashMap;
import java.util.Map;

public class UserSetting {

    public static UserInfo user = new UserInfo();
    private static SharedPreferences.Editor editor;
    private static SharedPreferences pref;


    //存入账号密码
    public static void setUserLoginInfo(Activity activity,String account,String password){
         editor = activity.getSharedPreferences("UserInfo", Context.MODE_PRIVATE).edit();
         editor.putString("account",account);
         editor.putString("password",password);
         editor.apply();
    }

    //获取账号密码
    public static Map<String,String> getUserLoginInfo(Activity activity){
        pref = activity.getSharedPreferences("UserInfo",Context.MODE_PRIVATE);
        Map<String,String> map = new HashMap<>();
        String account = pref.getString("account","");
        String password = pref.getString("password","");
        map.put("account",account);
        map.put("password",password);
        return map;
    }

    //保存是否自动登录
    public static void setAutoSave(Boolean flag){
        editor = BaseActivity.getCurrentActivity().getSharedPreferences("UserInfo",Context.MODE_PRIVATE).edit();
        editor.putString("autoSave",flag.toString());
        editor.apply();
    }

    //检查是否自动登录
    public static boolean getAutoSave(){
        pref = BaseActivity.getCurrentActivity().getSharedPreferences("UserInfo",Context.MODE_PRIVATE);
        String str = pref.getString("autoSave","false");
        return Boolean.parseBoolean(str);
    }

    //存入（从服务器获取的）用户信息
    public static void setUserInfo(UserInfo user){
        editor = BaseActivity.getCurrentActivity().getSharedPreferences("UserInfo",Context.MODE_PRIVATE).edit();
        editor.putString("name",user.getName());
        editor.putInt("unread",user.getUnread());
        editor.putInt("id",user.getId());
        editor.apply();
    }

    //取出（服务器获取的）用户信息
    public static UserInfo getUserInfo(){
        pref = BaseActivity.getCurrentActivity().getSharedPreferences("UserInfo",Context.MODE_PRIVATE);
        String name = pref.getString("name","");
        int id= pref.getInt("id",-1);
        int unread = pref.getInt("unread",0);
        return new UserInfo(id,name,unread);
    }

}
