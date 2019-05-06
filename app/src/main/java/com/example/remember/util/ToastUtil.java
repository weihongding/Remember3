package com.example.remember.util;

import android.widget.Toast;

public class ToastUtil {

    public ToastUtil(String message){
        Toast.makeText(MyApplication.getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
