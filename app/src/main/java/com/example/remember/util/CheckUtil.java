package com.example.remember.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.remember.R;
import com.example.remember.activity.MailActivity;
import com.example.remember.activity.RcActivity;
import com.example.remember.activity.SbActivity;
import com.example.remember.db.Mail;
import com.example.remember.db.Rc_unq;
import com.example.remember.entity.Sb;
import com.example.remember.entity.UserInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

//检查数据
public class CheckUtil {

    private static final int linkFail = 100860;
    private static final int loginSuccess = 100861;
    private static final int regSuccess = 100862;
    private static final int unreadSuccess = 100863;
    private static final int sbAllSuccess = 100864;
    private static final int sbSuccess = 100865;
    private static final int putShareSuccess = 100866;
    private static final int getShareSuccess = 100867;
    private static final int haveNewsSuccess = 100868;
    private static final int haveNotNewsSuccess = 100869;
    private static final int rcUnqBackupUpSuccess = 100870;
    private static final int rcUnqBackupDownSuccess = 100871;



    private static Handler mHandler = new Handler(){

        @RequiresApi(api = Build.VERSION_CODES.O)
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

                        if (UserSetting.getLogined()==1) {
                            int id = UserSetting.getIifId();
                            if (id != 0) {
                                Intent intent = new Intent(BaseActivity.getCurrentActivity(), ObjectUtil.getIifClass(id));
                                BaseActivity.getCurrentActivity().startActivity(intent);
                            }
                            UserSetting.setIsNotStart();
                        }

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
                case putShareSuccess:{
                    //分享连接成功

                    String shareText = msg.getData().getString("text");
                    JSONObject json = JSONObject.parseObject(shareText);
                    String desc = json.getString("desc");
                    int status = json.getInteger("status");
                    String data = json.getString("data");
                    new ToastUtil(desc);

                    if (status==ConstResponse.STATUS_OK){
                        haveNews(data);
                    }

                    break;
                }
                case getShareSuccess:{
                    //获取新信息成功

                    String shareText = msg.getData().getString("text");
                    JSONObject json = JSONObject.parseObject(shareText);
                    String desc = json.getString("desc");
                    int status = json.getInteger("status");
                    String data = json.getString("data");
                    List<Integer> list = new ArrayList<>();

                    if (status==ConstResponse.STATUS_OK){
                        JSONArray ja = JSONArray.parseArray(data);
                        for (int i = 0;i<ja.size();i++){
                            Mail mail = new Mail((String) ja.getString(i));
                            mail.save();
                            list.add(mail.getId());
                        }
                        UserSetting.setUserUnread(ConstResponse.unread_false);
                    }

                    MailActivity.mailList.clear();
                    MailActivity.mailList.addAll(DbUtil.requestMailList());
                    MailActivity.sort();

                    new ToastUtil(desc);
                    haveNotNews();

                    break;
                }
                case haveNewsSuccess:{
                    break;
                }
                case haveNotNewsSuccess:{
                    break;
                }
                case rcUnqBackupUpSuccess:{

                    String text = msg.getData().getString("text");
                    JSONObject json = JSONObject.parseObject(text);
                    String desc = json.getString("desc");
                    int status = json.getInteger("status");

                    if (status==ConstResponse.STATUS_OK){
                        new ToastUtil(desc);
                    }else{
                        new ToastUtil("上传失败");
                    }

                    break;
                }
                case rcUnqBackupDownSuccess:{

                    String text = msg.getData().getString("text");
                    JSONObject json = JSONObject.parseObject(text);
                    String desc = json.getString("desc");
                    int status = json.getInteger("status");
                    String data = json.getString("data");

                    if (status==ConstResponse.STATUS_OK){

                        data = DataUtil.getDecryptStringByTea(data);

                        JSONArray ja = JSON.parseArray(data);

                        List<Rc_unq> rcunqList = JSONObject.parseArray(ja.toJSONString(), Rc_unq.class);
                        for (Rc_unq rc_unq:rcunqList) {
                            rc_unq.save();
                        }
                        RcActivity.refreshUnq();
                        new ToastUtil("添加成功");

                    }else{
                        new ToastUtil(desc);
                    }

                    break;
                }

                default:{
                    break;
                }
            }
        }

    };

    public static void rcunqBackupDown(){

        RequestBody body = new FormBody.Builder()
                .add("account",UserSetting.getUserLoginInfo(BaseActivity.getCurrentActivity()).get("account"))
                .build();

        String url = HttpUtil.urlHead+StringUtil.httpUrl_rcunqBackupDown;
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
                    msg.what = rcUnqBackupDownSuccess;
                    Bundle bundle = new Bundle();
                    bundle.putString("text",responseText);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void rcunqBackupUp(String content){

        content = DataUtil.getEncryptStringByTea(content);

        RequestBody body = new FormBody.Builder()
                .add("account",UserSetting.getUserLoginInfo(BaseActivity.getCurrentActivity()).get("account"))
                .add("content",content)
                .build();

        String url = HttpUtil.urlHead+StringUtil.httpUrl_rcunqBackupUp;
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
                    msg.what = rcUnqBackupUpSuccess;
                    Bundle bundle = new Bundle();
                    bundle.putString("text",responseText);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }


            }
        });

    }

    public static void haveNews(String account){

        RequestBody body = new FormBody.Builder()
                .add("account",account)
                .build();

        String url = HttpUtil.urlHead+StringUtil.httpUrl_haveNews;
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
                    msg.what = haveNewsSuccess;
                    Bundle bundle = new Bundle();
                    bundle.putString("text",responseText);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }

            }
        });

    }

    public static void haveNotNews(){

        String account = UserSetting.getUserLoginInfo(BaseActivity.getCurrentActivity()).get("account");
        RequestBody body = new FormBody.Builder()
                .add("account",account)
                .build();

        String url = HttpUtil.urlHead+StringUtil.httpUrl_haveNotNews;
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
                    msg.what = haveNotNewsSuccess;
                    Bundle bundle = new Bundle();
                    bundle.putString("text",responseText);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }

            }
        });

    }

    //获取新信息
    public static void getShare(){

        DbUtil.delectAllMail();

        String account = UserSetting.getUserLoginInfo(BaseActivity.getCurrentActivity()).get("account");
        RequestBody body = new FormBody.Builder()
                .add("account",account)
                .build();

        String url = HttpUtil.urlHead+StringUtil.httpUrl_getNews;
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
                    msg.what = getShareSuccess;
                    Bundle bundle = new Bundle();
                    bundle.putString("text",responseText);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }

            }
        });

    }

    //将分享内容保存到服务器
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void putShare(String ac_re, String type, String objStr){
        JSONObject conJson = new JSONObject();
        conJson.put("type",type);
        conJson.put("objStr",objStr);
        String content = conJson.toJSONString();
        content = DataUtil.getEncryptStringByTea(content);

        JSONObject json = new JSONObject();
        json.put("ac_se",UserSetting.getUserLoginInfo(BaseActivity.getCurrentActivity()).get("account"));
        json.put("ac_re",ac_re);
        json.put("content",content);
        String mailStr = json.toJSONString();

        RequestBody body = new FormBody.Builder()
                .add("mailStr",mailStr)
                .build();

        String url = HttpUtil.urlHead+StringUtil.httpUrl_putNews;
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
                    msg.what = putShareSuccess;
                    Bundle bundle = new Bundle();
                    bundle.putString("text",responseText);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }
            }
        });

    }

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

            new LogdUtil((String)valueMap.get("account"));

            String url = HttpUtil.urlHead+StringUtil.httpUrl_login;
            new LogdUtil(url);
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
