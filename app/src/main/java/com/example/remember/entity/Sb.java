package com.example.remember.entity;

import com.example.remember.util.DateUtil;

public class Sb implements Comparable<Sb> {

    public static String choose_key = null;

    private String name;
    private String state;
    private String time;
    private String key;

    public Sb(String name,String state,String time){
        setName(name);
        setState(state);
        setTime(time);
        setKey(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int compareTo(Sb sb) {
        return DateUtil.getGapOfTime(sb.time,this.time);
    }
}
