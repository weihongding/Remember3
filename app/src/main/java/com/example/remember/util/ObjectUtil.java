package com.example.remember.util;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.alibaba.fastjson.JSONObject;
import com.example.remember.activity.BwlActivity;
import com.example.remember.activity.DtActivity;
import com.example.remember.activity.JlActivity;
import com.example.remember.activity.MainActivity;
import com.example.remember.activity.RcActivity;
import com.example.remember.activity.SbActivity;
import com.example.remember.activity.TqActivity;
import com.example.remember.db.Bwl_event;
import com.example.remember.db.Mail;
import com.example.remember.db.Rc_q;
import com.example.remember.db.Rc_unq;

import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ObjectUtil {

    public static Rc_unq rcqToRcunq(Rc_q rcq){
        return new Rc_unq(rcq.getTitle(),rcq.getDes(),rcq.getColor());
    }

    public static Rc_q rcunqToRcq(Rc_unq rc_unq,String time){
        return new Rc_q(time,rc_unq.getTitle(),rc_unq.getDes(),rc_unq.getColor());
    }

    public static Set strToSet(String str){
        Set set = new HashSet<>();
        int a = str.indexOf("[");
        int b = str.indexOf(",");
        while(b!=-1){
            a++;
            set.add(str.substring(a,b).trim());
            a = b;
            b = str.indexOf(",",a+1);
        }
        a++;
        b = str.indexOf("]");
        set.add(str.substring(a,b));
        return set;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String bytesToStr(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static byte[] strToBytes(String str){
        return Base64.getDecoder().decode(str);
    }

    //对象转String
    public static String objToStr(Object obj){
        JSONObject json = (JSONObject)JSONObject.toJSON(obj);
        return json.toJSONString();
    }

    //将mail转为备忘录
    public static Bwl_event mailToBe(Mail mail){

        JSONObject json = JSONObject.parseObject(mail.getObjStr());
        Bwl_event be = new Bwl_event(json.getString("color"),json.getString("content"), DateUtil.dateToStr(new Date()));
        return be;

    }
//    "日程","备忘录","记录","天气","地图","设备"
    public static Class getIifClass(int id){
        switch (id){
            case 1:{
                return RcActivity.class;
            }
            case 2:{
                return BwlActivity.class;
            }
            case 3:{
                return JlActivity.class;
            }
            case 4:{
                return TqActivity.class;
            }
            case 5:{
                return DtActivity.class;
            }
            case 6:{
                return SbActivity.class;
            }
            default:{
                return MainActivity.class;
            }
        }
    }

}
