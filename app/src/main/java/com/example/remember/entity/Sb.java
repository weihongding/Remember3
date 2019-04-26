package com.example.remember.entity;

public class Sb {

    private String name;
    private String status;
    private String time;

    public Sb(String name,String status,String time){
        setName(name);
        setStatus(status);
        setTime(time);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
