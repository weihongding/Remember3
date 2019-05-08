package com.example.remember.db;

import com.example.remember.util.DateUtil;

import org.litepal.crud.DataSupport;

import java.util.Date;

//事件记录
public class Jl_sj_event extends DataSupport implements Comparable<Jl_sj_event> {

    public static Jl_sj_event choose_jse = null;

    private int id;
    private int jid;//事件id
    private String time;//触发时间

    public Jl_sj_event(){}

    public Jl_sj_event(int Jlid){
        setJid(Jlid);
        setTime(DateUtil.dateToStr(new Date()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int compareTo(Jl_sj_event jse) {
        return DateUtil.getGapOfTime(jse.time,this.time);
    }

}
