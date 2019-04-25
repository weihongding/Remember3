package com.example.remember.util;

import android.content.Context;
import android.graphics.Typeface;

//设置font
public class FontManager {


    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface font = getTypeface();

    public static Typeface getTypeface() {
        return Typeface.createFromAsset(MyApplication.getContext().getAssets(), FONTAWESOME);
    }

}
