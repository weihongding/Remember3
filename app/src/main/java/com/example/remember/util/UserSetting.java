package com.example.remember.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.remember.entity.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        new LogdUtil("putAutoSave:"+flag);
        editor.apply();
    }

    //检查是否自动登录
    public static boolean getAutoSave(){
        pref = BaseActivity.getCurrentActivity().getSharedPreferences("UserInfo",Context.MODE_PRIVATE);
        String str = pref.getString("autoSave","false");
        new LogdUtil("getAutoSave:"+str);
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

    //设置未读情况
    public static void setUserUnread(int unread){
        editor = BaseActivity.getCurrentActivity().getSharedPreferences("UserInfo",Context.MODE_PRIVATE).edit();
        editor.putInt("unread",unread);
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

    //设置初始界面
    public static void setIifId(int iifId){
        editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();
        editor.putInt("iifId",iifId);
        editor.apply();
    }

    //取出设置的初始界面
    public static int getIifId(){
        pref = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        int iifId = pref.getInt("iifId",0);
        return iifId;
    }

    //设置备忘录字体大小
    public static void setBwlTextSize(int size){
        editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();
        editor.putInt("bwlTextSize",size);
        editor.apply();
    }

    //取出设置的初始界面
    public static int getBwlTextSize(){
        pref = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        int size = pref.getInt("bwlTextSize",18);
        return size;
    }

    //设置是否已登录 已登录1 未登录0
    public static void setLogined(int flag){
        editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();
        editor.putInt("flag",flag);
        editor.apply();
    }

    //取出设置的初始界面
    public static int getLogined(){
        pref = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        int iifId = pref.getInt("flag",0);
        return iifId;
    }

    //设置是启动
    public static void setIsStart(){
        editor = BaseActivity.getCurrentActivity().getSharedPreferences("InterFace",Context.MODE_PRIVATE).edit();
        editor.putInt("start",1);
        editor.apply();
    }

    //设置非启动
    public static void setIsNotStart(){
        editor = BaseActivity.getCurrentActivity().getSharedPreferences("InterFace",Context.MODE_PRIVATE).edit();
        editor.putInt("start",0);
        editor.apply();
    }

    //获取是否启动
    public static int getStart(){
        pref = BaseActivity.getCurrentActivity().getSharedPreferences("InterFace",Context.MODE_PRIVATE);
        int iifId = pref.getInt("start",1);
        return iifId;
    }


    //设置是否加载完
    public static void setOk(int ok){
        editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();
        editor.putInt("ok",ok);
        editor.apply();
    }

    public static int getOk(){
        pref = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        int iifId = pref.getInt("ok",0);
        return iifId;
    }

    //添加设备key
    public static void putSbKey(String key){
        Set set = getSbKey();
        set.add(key);
        setSbKey(set);
    }

    //删除设备key
    public static void delSbKey(String key){
        Set set = getSbKey();
        set.remove(key);
        setSbKey(set);
    }

    //设置设备keys
    public static void setSbKey(Set<String> set){
        editor = BaseActivity.getCurrentActivity().getSharedPreferences("SbInfo",Context.MODE_PRIVATE).edit();
        editor.putString("Key",set.toString());
        editor.apply();
    }

    //获得设备keys
    public static Set getSbKey(){
        pref = BaseActivity.getCurrentActivity().getSharedPreferences("SbInfo",Context.MODE_PRIVATE);
        String keyStr = pref.getString("Key","[]");
        return ObjectUtil.strToSet(keyStr);
    }

    //根据key设置设备备注
    public static void setKeyRemark(String key,String remark){
        editor = BaseActivity.getCurrentActivity().getSharedPreferences("SbInfo",Context.MODE_PRIVATE).edit();
        editor.putString(key,remark);
        editor.apply();
    }

    //根据key获得备注
    public static String getKeyRemark(String key){
        pref = BaseActivity.getCurrentActivity().getSharedPreferences("SbInfo",Context.MODE_PRIVATE);
        return pref.getString(key,"");
    }

}
