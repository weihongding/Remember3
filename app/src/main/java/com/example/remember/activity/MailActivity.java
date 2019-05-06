package com.example.remember.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.remember.R;
import com.example.remember.util.HttpUtil;
import com.example.remember.util.StringUtil;
import com.example.remember.util.ToastUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MailActivity extends AppCompatActivity {

    public SwipeRefreshLayout swipeRefresh;
    public TextView tv;

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
                Toast.makeText(MailActivity.this, "刷新中", Toast.LENGTH_SHORT).show();
                swipeRefresh.setRefreshing(false);
            }
        });

        requestMessage();

    }

    private void requestMessage(){

        String mailUrl = HttpUtil.urlHead+ StringUtil.httpUrl_getJson;
        HttpUtil.sendOkHttpRequest(mailUrl, new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final JSONObject json = new JSONObject().parseObject(responseText);//将String转为fastJson
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("获得的数据："+json.getString("a"));
                        new ToastUtil("获取邮箱信息成功");
                    }
                });
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MailActivity.this, "获取邮箱信息失败", Toast.LENGTH_SHORT).show();
            }

        });

    }

}
