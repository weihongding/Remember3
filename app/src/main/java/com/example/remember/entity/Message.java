package com.example.remember.entity;

public class Message {

    private String sentUser;
    private String getUser;
    private String message;

    public Message(String sentUser,String getUser,String message){
        setSentUser(sentUser);
        setGetUser(getUser);
        setMessage(message);
    }

    public String getSentUser() {
        return sentUser;
    }

    public void setSentUser(String sentUser) {
        this.sentUser = sentUser;
    }

    public String getGetUser() {
        return getUser;
    }

    public void setGetUser(String getUser) {
        this.getUser = getUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
