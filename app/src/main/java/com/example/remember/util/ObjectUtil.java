package com.example.remember.util;

import com.example.remember.db.Rc_q;
import com.example.remember.db.Rc_unq;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ObjectUtil {

    public static Rc_unq rcqToRcunq(Rc_q rcq){
        return new Rc_unq(rcq.getTitle(),rcq.getDes(),rcq.getColor());
    }

    public static Rc_q rcunqToRcq(Rc_unq rc_unq,String time){
        return new Rc_q(time,rc_unq.getTitle(),rc_unq.getDes(),rc_unq.getColor());
    }

}
