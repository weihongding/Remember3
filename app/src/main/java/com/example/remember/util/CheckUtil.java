package com.example.remember.util;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.remember.R;
import com.example.remember.ccs.Client;
import com.example.remember.entity.UserInfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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


    private static Handler mHandler = new Handler(){

        public void handleMessage(Message msg){
            switch (msg.what){
                case linkFail:{
                    new ToastUtil("网络链接请求失败");
                }
                case loginSuccess:{
                    //登陆链接成功
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
                }
                default:{
                    break;
                }
            }
        }

    };

    //注册账号
    public static void register(String account,String password,String username){
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
