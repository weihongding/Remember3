package com.example.remember.db;

import org.litepal.crud.DataSupport;

public class Rc_unq extends DataSupport {

    private String content;
    private String des;
    private String color;

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
}
