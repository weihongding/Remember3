package com.example.remember.db;

import com.example.remember.util.ColorUtil;
import com.example.remember.util.DateUtil;
import com.example.remember.util.StringUtil;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.Date;

public class Bwl_event extends DataSupport implements Comparable<Bwl_event>, Serializable {

    public static Bwl_event choose_be = null;

    private int id;
    private String color;
    private String content;
    private String time;

    public Bwl_event(){
        setTime(DateUtil.dateToStr(new Date()));
        setColor(ColorUtil.colors[0]);
        setContent("");
        setId(-1);
    };

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
        return DateUtil.getGapOfTime(be.time,this.time);
    }
}
