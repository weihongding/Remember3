package com.example.remember.db;

import com.example.remember.util.DateUtil;

import org.litepal.crud.DataSupport;

public class Rc_q extends DataSupport implements Comparable<Rc_q>{

    public static Rc_q chooseRcq = null;

    private int id;
    private String time;
    private String title;
    private String des;
    private String color;

    public Rc_q(){}

    public Rc_q(String time,String title,String des,String color){
        setTime(time);
        setTitle(title);
        setDes(des);
        setColor(color);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return DateUtil.getSofB(this.getTime())- DateUtil.getSofB(r.getTime());
    }
}
