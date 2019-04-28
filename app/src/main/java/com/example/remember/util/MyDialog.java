package com.example.remember.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MyDialog extends Dialog {

    public static MyDialog colorDialog;
    public static MyDialog loginDialog;
    public static MyDialog regDialog;

    public static MyDialog rcqDialog;
    public static MyDialog rcqDialog_set;
    public static MyDialog rcqDialog_add;



    //    style引用style样式
    public MyDialog(Context context, View layout, int style) {

        super(context, style);
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }
}