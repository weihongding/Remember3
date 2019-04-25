package com.example.remember.db;

import com.example.remember.util.DateUtil;

import org.litepal.crud.DataSupport;

public class Rc_q extends DataSupport implements Comparable<Rc_q>{

    private String startTime;
    private String endTime;
    private String content;
    private String des;
    private String color;

    public Rc_q(){}

    public Rc_q(String startTime,String endTime,String content,String des,String color){
        setStartTime(startTime);
        setEndTime(endTime);
        setContent(content);
        setDes(des);
        setColor(color);
    }
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int compareTo(Rc_q r) {
        return DateUtil.getSofB(this.getStartTime())- DateUtil.getSofB(r.getStartTime());
    }
}
