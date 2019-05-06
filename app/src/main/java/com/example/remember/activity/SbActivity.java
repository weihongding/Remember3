package com.example.remember.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.adapter.SbAdapter;
import com.example.remember.entity.Sb;
import com.example.remember.listener.BtnListener;
import com.example.remember.util.BaseActivity;
import com.example.remember.util.CheckUtil;
import com.example.remember.util.DataUtil;
import com.example.remember.util.DateUtil;
import com.example.remember.util.StringUtil;
import com.example.remember.util.UserSetting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.InflaterInputStream;

public class SbActivity extends BaseActivity {

    public SwipeRefreshLayout swipeRefresh;
    public static List<Sb> sbList;
    public static SbAdapter sbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sb);

        //初始化数据
        iniSbList();

        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_sb_refresh);
        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_sb);
        Button btn_add = (Button)findViewById(R.id.btn_sb_add);

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);

        btn_add.setOnClickListener(BtnListener.instance);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipeRefresh.setRefreshing(false);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        sbAdapter = new SbAdapter(sbList);
        rv.setAdapter(sbAdapter);

        refresh();

    }

    private void iniSbList(){

        if (sbList == null){sbList = new ArrayList<>();}

        Set<String> set = new HashSet<String>();
        set.add("sb1");
        set.add("sb2");

        UserSetting.setSbKey(set);

    }

    public static void sort(){
        Collections.sort(sbList);
        sbAdapter.notifyDataSetChanged();
    }

    public static void refresh(){

        CheckUtil.getSbInfoAll();

    }

}
