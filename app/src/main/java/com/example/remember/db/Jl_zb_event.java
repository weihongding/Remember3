package com.example.remember.db;

import com.example.remember.util.DateUtil;

import org.litepal.crud.DataSupport;

import java.util.Date;

//指标记录
public class Jl_zb_event extends DataSupport implements Comparable<Jl_zb_event>{

    public static Jl_zb_event choose_jze = null;

    private int id;
    private int jid;//指标id
    private String time;//触发时间
    private String content;//记录内容

    public Jl_zb_event(){}

    public Jl_zb_event(int Jlid,String content){
        setJid(Jlid);
        setContent(content);
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(Jl_zb_event jze) {
        return DateUtil.getGapOfTime(jze.time,this.time);
    }
}
