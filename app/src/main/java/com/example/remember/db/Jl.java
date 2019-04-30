package com.example.remember.db;

import com.example.remember.util.DateUtil;

import org.litepal.crud.DataSupport;

//指标记录
public class Jl extends DataSupport implements Comparable<Jl> {

    private int id;
    private String type;
    private String title;
    private String des;
    private String color;
    private String finTime;

//    public Jl(){}

    public Jl(String type,String title,String des,String color){
        setType(type);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getFinTime() {
        return finTime;
    }

    public void setFinTime(String finTime) {
        this.finTime = finTime;
    }

    @Override
    public int compareTo(Jl jl) {
        return DateUtil.getGapOfTime(jl.finTime,this.finTime);
    }
}
