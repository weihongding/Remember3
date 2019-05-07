package com.example.remember.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.remember.R;
import com.example.remember.adapter.MailAdapter;
import com.example.remember.db.Mail;
import com.example.remember.util.CheckUtil;
import com.example.remember.util.ConstResponse;
import com.example.remember.util.DbUtil;
import com.example.remember.util.StringUtil;
import com.example.remember.util.ToastUtil;
import com.example.remember.util.UserSetting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MailActivity extends AppCompatActivity {

    public SwipeRefreshLayout swipeRefresh;
    public TextView tv;
    public static List<Mail> mailList;
    public static MailAdapter mailAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        iniMailList();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_mail);
        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_mail_refresh);

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        mailAdapter = new MailAdapter(mailList);
        rv.setAdapter(mailAdapter);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipeRefresh.setRefreshing(false);
            }
        });

        sort();

    }

    private void iniMailList(){

        if (mailList == null){
            mailList = new ArrayList<>();
        }

        mailList = DbUtil.requestMailList();

        if(UserSetting.getUserInfo().getUnread()== ConstResponse.unread_true){
            new ToastUtil("有新消息");
        }else{
            CheckUtil.unread();
            if(UserSetting.getUserInfo().getUnread()== ConstResponse.unread_true){
                new ToastUtil("有新消息");
            }
        }

    }

    public static void sort(){
        Collections.sort(mailList);
        mailAdapter.notifyDataSetChanged();
    }

    private void refresh(){

        CheckUtil.unread();
        if(UserSetting.getUserInfo().getUnread()== ConstResponse.unread_true){
            //如果有新信息则发起请求
            CheckUtil.getShare();
        }else{
            new ToastUtil("无新消息");
        }
        sort();

    }

}