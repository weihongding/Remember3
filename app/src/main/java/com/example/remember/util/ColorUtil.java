package com.example.remember.util;

public class ColorUtil {

    public static final String[] colors = {"#CED2E8","#D2ABB9","#F9DBDC","#CAEEEB","#C3E3C2","#EAD4B4","#CDEAAB",
            "#636CA2","#FF5A92","#EC5156","#32C8BA","#4AEC49","#F0AA41","#8CCC43",
            "#717586","#A44F6C","#BF6467","#4E8C86","#488C47","#C39B5F"};

    public static final String state_open = "#D4ECB9";
    public static final String state_close = "#666873";

    public static String choose_color = null;

    public static String getStateColor(String state){
        if (state.equals(StringUtil.state_sb[1])){
            return state_open;
        }
        return state_close;
    }

}
