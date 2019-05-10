package com.example.remember.db;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.alibaba.fastjson.JSONObject;
import com.example.remember.util.DataUtil;
import com.example.remember.util.LogdUtil;

import org.litepal.crud.DataSupport;

public class Mail extends DataSupport implements Comparable<Mail>{

    public static Mail choose_mail = null;

    private int id;
    private String sentUser;
    private String type;
    private String objStr;

    public Mail(){
        super();
    }

    public Mail(String sentUser,String type){
        setSentUser(sentUser);
        setType(type);
    }

    public Mail(int id,String sentUser,String type,String objStr){
        setId(id);
        setSentUser(sentUser);
        setType(type);
        setObjStr(objStr);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Mail(String mailStr){

        JSONObject json = JSONObject.parseObject(mailStr);
        int id = json.getInteger("id");
        String ac_se = json.getString("ac_se");
        String content = json.getString("content");
        setId(id);
        setSentUser(ac_se);
        content = DataUtil.getDecryptStringByTea(content);
        JSONObject conJson = JSONObject.parseObject(content);
        String type = conJson.getString("type");
        String objStr = conJson.getString("objStr");
        setType(type);
        setObjStr(objStr);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSentUser() {
        return sentUser;
    }

    public void setSentUser(String sentUser) {
        this.sentUser = sentUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjStr() {
        return objStr;
    }

    public void setObjStr(String objStr) {
        this.objStr = objStr;
    }

    @Override
    public int compareTo(Mail mail) {
        return (mail.getId()-this.getId());
    }
}
