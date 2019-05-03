package com.example.remember.util;

import com.example.remember.db.Rc_q;
import com.example.remember.db.Rc_unq;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
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

}
