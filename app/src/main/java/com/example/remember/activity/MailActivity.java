package com.example.remember.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.remember.R;
import com.example.remember.util.CheckUtil;
import com.example.remember.util.ConstResponse;
import com.example.remember.util.DataUtil;
import com.example.remember.util.HttpUtil;
import com.example.remember.util.StringUtil;
import com.example.remember.util.TeaUtil;
import com.example.remember.util.ToastUtil;
import com.example.remember.util.UserSetting;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MailActivity extends AppCompatActivity {

    public SwipeRefreshLayout swipeRefresh;
    public TextView tv;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

//        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_mail);
        tv = (TextView)findViewById(R.id.text_mail);
        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_mail_refresh);


        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestMessage();
                swipeRefresh.setRefreshing(false);
            }
        });

        requestMessage();

    }

    private void requestMessage(){

        CheckUtil.unread();

        if(UserSetting.getUserInfo().getUnread()== ConstResponse.unread_true){
            //如果有新信息则发起请求




        }


    }

}