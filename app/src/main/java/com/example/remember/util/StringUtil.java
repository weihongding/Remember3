package com.example.remember.util;

import com.alibaba.fastjson.JSON;

public class StringUtil {

    public static final String eventType_rc ="日程";
    public static final String eventType_bwl = "备忘录";
    public static final String eventType_zb = "指标";
    public static final String eventType_sj = "事件";
    public static final String eventType_sb = "设备";

    public static final String[] state_sb = {"关","开"};
    public static final String[] iniInF = {"默认","日程","备忘录","记录","天气","地图","设备"};

    public static final String httpUrl_getJson = "/user/getJson";
    public static final String httpUrl_login = "/user/login";
    public static final String httpUrl_register = "/user/register";
    public static final String httpUrl_unread = "/user/unread";
    public static final String httpUrl_sb = "/sb/getinfo";
    public static final String httpUrl_sbAll = "/sb/getinfoAll";
    public static final String httpUrl_putNews = "/mail/putNews";
    public static final String httpUrl_getNews = "/mail/getNews";
    public static final String httpUrl_haveNews = "/user/haveUnread";
    public static final String httpUrl_haveNotNews = "/user/haveNotUnread";

    public static String numToStr(int n){

        switch (n){
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";
            case 10:
                return "十";
            case 11:
                return "十一";
            case 12:
                return "十二";
        }

        return null;
    }

    public static String getContent_bwl(String str){
        str = str.trim();
        while (str.startsWith(" ")){
            str = str.substring(1,str.length()).trim();
        }
        if (str.length()>10){
            return str.substring(0,10)+"...";
        }else{
            return str;
        }
    }

    public static String conDateStr(String year,String month,String day,String H,String M,String S){
        return year+"-"+month+"-"+day+" "+H+":"+M+":"+S;
    }

}
