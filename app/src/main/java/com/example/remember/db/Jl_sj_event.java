package com.example.remember.db;

import org.litepal.crud.DataSupport;

//事件记录
public class Jl_sj_event extends DataSupport {

    private int id;
    private int jid;//事件id
    private int time;//触发时间

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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
