package com.example.remember.db;

import org.litepal.crud.DataSupport;

public class Rc_unq extends DataSupport {

    public static Rc_unq chooseRcUnq = null;

    private int id;
    private String title;
    private String des;
    private String color;

    public Rc_unq(){}
    public Rc_unq(String title,String des,String color){
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
}
