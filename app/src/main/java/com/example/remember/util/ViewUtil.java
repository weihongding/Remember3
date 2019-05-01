package com.example.remember.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.remember.R;
import com.example.remember.adapter.ColorAdapter;

//设置font
public class ViewUtil {


    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface font = getTypeface();

    public static Typeface getTypeface() {
        return Typeface.createFromAsset(MyApplication.getContext().getAssets(), FONTAWESOME);
    }

    public static void setViewColor(View view,String color){
        GradientDrawable p = (GradientDrawable) view.getBackground();
        p.setColor(Color.parseColor(color));
    }

    public static void setChooseColor(View view){
        GradientDrawable p = (GradientDrawable) view.getBackground();
        if (ColorUtil.choose_color != null) {
            p.setColor(Color.parseColor(ColorUtil.choose_color));
        }
    }

}
