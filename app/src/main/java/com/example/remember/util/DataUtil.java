package com.example.remember.util;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class DataUtil {

    public static String TAG = "MainActivityLog";

    //MD5数据加密
    public static String getMd5(String plainText) {
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    //将普通的string格式转化为jsonStr格式
    //输入格式： key1:value1,key2:value2 ...
    //返回格式： {"key1":"value1","key2":"value2"}
    public static String getJsonStr(String str){
        str = "{\""+str+"\"}";
        str = str.replace(":", "\":\"");
        str = str.replace(",", "\",\"");
        return str;
    }

    //fastjson转map
    public static Map jsonToMap(JSONObject json){
        Map map = (Map) json;
        return map;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getEncryptStringByTea(String info){
        return TeaUtil.getEncryptStringByTea(info);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDecryptStringByTea(String info){
        return TeaUtil.getDecryptStringByTea(info);
    }

}
