package com.example.remember.entity;

public class UserInfo {

    private int id = -1;
    private String name = "未命名用户";
    private int unread = 0;

    public UserInfo(){}

    public UserInfo(int id,String name,int unread){
        setId(id);
        setName(name);
        setUnread(unread);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }
}
