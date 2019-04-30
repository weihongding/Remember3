package com.example.remember.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

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

}
