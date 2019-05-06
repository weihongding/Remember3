package com.example.remember.entity;

import com.example.remember.util.DateUtil;

public class Sb implements Comparable<Sb> {

    private String name;
    private String state;
    private String time;

    public Sb(String name,String state,String time){
        setName(name);
        setState(state);
        setTime(time);
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

    @Override
    public int compareTo(Sb sb) {
        return DateUtil.getGapOfTime(sb.time,this.time);
    }


}
