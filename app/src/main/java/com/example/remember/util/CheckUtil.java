package com.example.remember.util;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.remember.R;
import com.example.remember.activity.SbActivity;
import com.example.remember.ccs.Client;
import com.example.remember.entity.Sb;
import com.example.remember.entity.UserInfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

//检查数据
public class CheckUtil {

    private static final int linkFail = 100860;
    private static final int loginSuccess = 100861;
    private static final int regSuccess = 100862;
    private static final int unreadSuccess = 100863;
    private static final int sbAllSuccess = 100864;
    private static final int sbSuccess = 100865;


    private static Handler mHandler = new Handler(){

        public void handleMessage(Message msg){
            switch (msg.what){
                case linkFail:{
                    new ToastUtil("网络连接请求失败");
                    break;
                }
                case loginSuccess:{
                    //登陆连接成功
                    Button btn_login = (Button)BaseActivity.getCurrentActivity().findViewById(R.id.btn_login);
                    TextView tv_user = (TextView)BaseActivity.getCurrentActivity().findViewById(R.id.text_user);

                    String loginText = msg.getData().getString("text");
                    JSONObject json = JSONObject.parseObject(loginText);

                    String desc = json.getString("desc");
                    int status = json.getInteger("status");
                    String data = json.getString("data");

                    new ToastUtil(desc);
                    if (status==ConstResponse.STATUS_OK){
                        JSONObject userJson = JSONObject.parseObject(data);
                        UserInfo user = new UserInfo(userJson.getInteger("id"),userJson.getString("username"),userJson.getInteger("unread"));
                        UserSetting.setUserInfo(user);
                        tv_user.setText("欢迎使用，"+user.getName());
                        btn_login.setVisibility(View.GONE);
                        tv_user.setVisibility(View.VISIBLE);
                    }
                    break;
                }
                case regSuccess:{
                    //注册链接成功

                    String regsText = msg.getData().getString("text");
                    JSONObject json = JSONObject.parseObject(regsText);

                    String desc = json.getString("desc");
                    int status = json.getInteger("status");

                    new ToastUtil(desc);
                    if (status==ConstResponse.STATUS_OK){
                        MyDialog.regDialog.cancel();
                        MyDialog.loginDialog.show();
                    }
                    break;
                }
                case unreadSuccess:{
                    //检查未读连接成功

                    String unreadText = msg.getData().getString("text");
                    JSONObject json = JSONObject.parseObject(unreadText);

                    String desc = json.getString("desc");
                    int status = json.getInteger("status");

                    new ToastUtil(desc);
                    if (status==ConstResponse.STATUS_OK){
                        UserSetting.setUserUnread(ConstResponse.unread_true);
                    }
                    break;
                }
                case sbAllSuccess:{
                    //获取所有设备信息连接成功

                    String sbText = msg.getData().getString("text");
                    JSONObject json = JSONObject.parseObject(sbText);

                    String desc = json.getString("desc");
                    int status = json.getInteger("status");
                    String data = json.getString("data");

                    new ToastUtil(desc);
                    if (status==ConstResponse.STATUS_OK){

                        SbActivity.sbList.clear();
                        JSONArray ja = JSONArray.parseArray(data);
                        for (int i=0;i<ja.size();i++){
                            JSONObject jo = JSONObject.parseObject(ja.getString(i));
                            Sb sb = new Sb(jo.getString("name"),jo.getString("state"),jo.getString("time"));
                            String remark = UserSetting.getKeyRemark(jo.getString("name"));
                            if (!isEmpty(remark)){
                                sb.setName(remark);
                            }
                            SbActivity.sbList.add(sb);
                            SbActivity.sort();
                        }

                    }

                    break;
                }
                case sbSuccess:{
                    //获取单个设备信息连接成功

                    String sbText = msg.getData().getString("text");
                    JSONObject json = JSONObject.parseObject(sbText);
                    String desc = json.getString("desc");
                    int status = json.getInteger("status");
                    String key = json.getString("data");

                    new ToastUtil(desc);
                    if (status==ConstResponse.STATUS_OK){

                        if(UserSetting.getSbKey().toString().length()<=2){
                            Set set = new HashSet();
                            set.add(key);
                            UserSetting.setSbKey(set);
                        }else{
                            UserSetting.putSbKey(key);
                        }

                        SbActivity.refresh();

                    }

                }
                default:{
                    break;
                }
            }
        }

    };

    //查询单个设备是否存在
    public static void exisSb(String key){
        RequestBody body = new FormBody.Builder()
                .add("key",key)
                .build();
        String url = HttpUtil.urlHead+StringUtil.httpUrl_sb;
        HttpUtil.sendOkHttpRequest(url, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = linkFail;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                if (responseText==null||responseText.equals("")){
                    Message msg = new Message();
                    msg.what = linkFail;
                    mHandler.sendMessage(msg);
                }else{
                    Message msg = new Message();
                    msg.what = sbSuccess;
                    Bundle bundle = new Bundle();
                    bundle.putString("text",responseText);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }

            }
        });

    }

    //获取所有设备信息
    public static void getSbInfoAll(){

        String keyStr = UserSetting.getSbKey().toString();

        if(keyStr.length()<=2){
            return;
        }

        RequestBody body = new FormBody.Builder()
                .add("keySet",keyStr)
                .build();
        String url = HttpUtil.urlHead+StringUtil.httpUrl_sbAll;

        HttpUtil.sendOkHttpRequest(url, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = linkFail;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                if (responseText==null||responseText.equals("")){
                    Message msg = new Message();
                    msg.what = linkFail;
                    mHandler.sendMessage(msg);
                }else{
                    Message msg = new Message();
                    msg.what = sbAllSuccess;
                    Bundle bundle = new Bundle();
                    bundle.putString("text",responseText);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }
            }
        });

    }

    //检查是否有未读的信息
    public static void unread(){

        String account = UserSetting.getUserLoginInfo(BaseActivity.getCurrentActivity()).get("account");
        RequestBody body = new FormBody.Builder()
                .add("account",account)
                .build();

        String url = HttpUtil.urlHead+StringUtil.httpUrl_unread;

        HttpUtil.sendOkHttpRequest(url, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = linkFail;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                if (responseText==null||responseText.equals("")){
                    Message msg = new Message();
                    msg.what = linkFail;
                    mHandler.sendMessage(msg);
                }else{
                    Message msg = new Message();
                    msg.what = unreadSuccess;
                    Bundle bundle = new Bundle();
                    bundle.putString("text",responseText);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }

            }
        });


    }

    //注册账号
    public static void register(String account,String password,String username){
        password = DataUtil.getMd5(password);
        RequestBody body = new FormBody.Builder()
                .add("account",account)
                .add("password",password)
                .add("username",username)
                .build();
        String url = HttpUtil.urlHead+StringUtil.httpUrl_register;
        HttpUtil.sendOkHttpRequest(url, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = linkFail;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                if (responseText==null||responseText.equals("")){
                    Message msg = new Message();
                    msg.what = linkFail;
                    mHandler.sendMessage(msg);
                }else{
                    Message msg = new Message();
                    msg.what = regSuccess;
                    Bundle bundle = new Bundle();
                    bundle.putString("text",responseText);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }
            }
        });
    }

    //根据用户名情况设置登陆情况
    public static void setUserLoginState(Activity activity){
        Button btn_login = (Button)activity.findViewById(R.id.btn_login);
        TextView tv_user = (TextView)activity.findViewById(R.id.text_user);
        if(UserSetting.getUserLoginInfo(activity).get("account").equals("")||UserSetting.getUserLoginInfo(activity).get("account")==null){
            btn_login.setVisibility(View.VISIBLE);
            tv_user.setVisibility(View.GONE);
        }else{
            Map valueMap = UserSetting.getUserLoginInfo(activity);

            RequestBody body = new FormBody.Builder()
                    .add("account", (String) valueMap.get("account"))
                    .add("password",(String)valueMap.get("password"))
                    .build();

            String url = HttpUtil.urlHead+StringUtil.httpUrl_login;
            HttpUtil.sendOkHttpRequest(url,body, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Message msg = new Message();
                    msg.what = linkFail;
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    if (responseText==null||responseText.equals("")){
                        Message msg = new Message();
                        msg.what = linkFail;
                        mHandler.sendMessage(msg);
                    }else{
                        Message msg = new Message();
                        msg.what = loginSuccess;
                        Bundle bundle = new Bundle();
                        bundle.putString("text",responseText);
                        msg.setData(bundle);
                        mHandler.sendMessage(msg);
                    }
                }
            });

        }
    }

    //检查是否符合为空或者只含有空格
    public static boolean isEmpty(String str){

        if (str == null){
            return true;
        }else if (str.trim().equals("")){
            return true;
        }

        return false;
    }

}
