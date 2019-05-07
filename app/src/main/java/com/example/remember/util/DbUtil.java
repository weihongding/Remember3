package com.example.remember.util;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.remember.db.Bwl_event;
import com.example.remember.db.Jl;
import com.example.remember.db.Jl_sj_event;
import com.example.remember.db.Jl_zb_event;
import com.example.remember.db.Mail;
import com.example.remember.db.Rc_q;
import com.example.remember.db.Rc_unq;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbUtil {
    //查询确定日程表中的数据
    public static void requestRcqList(List<String> ymdList,Map<String,List<Rc_q>> map){
        map.get("0").clear();
        map.get("1").clear();
        map.get("2").clear();
        map.get("3").clear();
        map.get("4").clear();
        map.get("5").clear();
        map.get("6").clear();
        List<Rc_q> rcqList = DataSupport.findAll(Rc_q.class);
        for (Rc_q rcq:rcqList) {
            String time = DateUtil.getYMD(rcq.getTime());
            if (time.equals(ymdList.get(0))){map.get("0").add(rcq);}
            if (time.equals(ymdList.get(1))){map.get("1").add(rcq);}
            if (time.equals(ymdList.get(2))){map.get("2").add(rcq);}
            if (time.equals(ymdList.get(3))){map.get("3").add(rcq);}
            if (time.equals(ymdList.get(4))){map.get("4").add(rcq);}
            if (time.equals(ymdList.get(5))){map.get("5").add(rcq);}
            if (time.equals(ymdList.get(6))){map.get("6").add(rcq);}
        }

    }

    //查询待定日程表中的数据
    public static List requestRcUnqList(){
        return DataSupport.findAll(Rc_unq.class);
    }

    //查询备忘录的数据
    public static List requestBwlEventList(){
        return DataSupport.findAll(Bwl_event.class);
    }

    //查询记录的数据
    public static List requestJlList(){
        return DataSupport.findAll(Jl.class);
    }

    //查询记录的详细数据
    public static List requestJlDetailList(String type,int jlId){
        if (type.equals(StringUtil.eventType_zb)){
            return DataSupport.where("jid = ?",Integer.toString(jlId)).find(Jl_zb_event.class);
        } else if (type.equals(StringUtil.eventType_sj)){
            return DataSupport.where("jid = ?",Integer.toString(jlId)).find(Jl_sj_event.class);
        }
        return null;
    }

    //查询消息的数据
    public static List<Mail> requestMailList(){
        return DataSupport.findAll(Mail.class);
    }

    public static void delectAllMail(){
        List<Mail> list = requestMailList();
        for (Mail mail:list) {
            mail.delete();
        }
    }

}
