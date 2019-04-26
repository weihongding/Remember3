package com.example.remember.db;

import com.example.remember.util.DateUtil;
import com.example.remember.util.StringUtil;

import org.litepal.crud.DataSupport;

public class Bwl_event extends DataSupport implements Comparable<Bwl_event>{

    private int id;
    private String color;
    private String content;
    private String time;

    public Bwl_event(){};

    public Bwl_event(String color, String content,String time){
        setColor(color);
        setContent(content);
        setTime(time);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int compareTo(Bwl_event be) {
        return DateUtil.getGapOfTime(this.time,be.time);
    }
}
