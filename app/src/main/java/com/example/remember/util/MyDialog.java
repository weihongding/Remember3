package com.example.remember.util;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.remember.R;
import com.example.remember.adapter.ColorAdapter;

public class MyDialog extends Dialog {

    public static MyDialog colorDialog_jl;
    public static MyDialog colorDialog_bwl;
    public static MyDialog colorDialog_rcq_set;
    public static MyDialog colorDialog_rcq_add;
    public static MyDialog colorDialog_rcUnq_set;
    public static MyDialog loginDialog;
    public static MyDialog regDialog;

    public static MyDialog rcqDialog;
    public static MyDialog rcqDialog_set;
    public static MyDialog rcqDialog_add;

    public static MyDialog jlDialog_add;

    public static MyDialog bwlDialog_long;
    public static MyDialog jlDialog_long;
    public static MyDialog jlDialog_item;
    public static MyDialog sbDialog_add;
    public static MyDialog sbDialog_long;

    public static MyDialog bwlDialog_share;
    public static MyDialog jlDialog_share;
    public static MyDialog sbDialog_share;

    public static MyDialog rcunqDialog;
    public static MyDialog rcunqDialog_set;


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