package com.example.remember.db;

import org.litepal.crud.DataSupport;

//指标记录
public class Jl_zb_event extends DataSupport {

    private int id;
    private int jid;//指标id
    private String time;//触发时间
    private String content;//记录内容

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
}
