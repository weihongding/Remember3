package com.example.remember.util;

import android.util.Log;

public class LogdUtil {

    public LogdUtil(String str){
        Log.d(DataUtil.TAG, str);
    }

    public LogdUtil(int i){
        Log.d(DataUtil.TAG, Integer.toString(i));
    }


}
