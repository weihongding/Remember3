package com.example.remember.util;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.remember.db.Rc_q;
import com.example.remember.db.Rc_unq;

import java.util.Base64;
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

}
