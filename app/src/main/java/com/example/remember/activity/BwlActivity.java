package com.example.remember.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.example.remember.R;
import com.example.remember.adapter.BwlAdapter;
import com.example.remember.adapter.ColorAdapter;
import com.example.remember.db.Bwl_event;
import com.example.remember.listener.BtnListener;
import com.example.remember.util.BaseActivity;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.MyDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BwlActivity extends BaseActivity {

    public static List<Bwl_event> bwlList;
    public static BwlAdapter bwlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bwl);

        //初始化数据
        iniBwlList();

        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_bwl);
        Button btn_add = (Button)findViewById(R.id.btn_bwl_add);

        BtnListener listener = new BtnListener(this);

        btn_add.setOnClickListener(listener);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        bwlAdapter = new BwlAdapter(bwlList);
        rv.setAdapter(bwlAdapter);

        sort();

    }

    private void iniBwlList(){
        bwlList = new ArrayList<>();
        Bwl_event be1 = new Bwl_event(ColorUtil.colors[1],"一二三四五六七八九十一","2019-4-8 15:03:59");
        Bwl_event be2 = new Bwl_event(ColorUtil.colors[2],"一二三四五","2019-4-24 15:03:59");
        bwlList.add(be1);
        bwlList.add(be2);
        bwlList.add(be1);
        bwlList.add(be2);
        bwlList.add(be1);
        bwlList.add(be2);
        bwlList.add(be1);
        bwlList.add(be2);
        bwlList.add(be1);
        bwlList.add(be2);
        bwlList.add(be1);
        bwlList.add(be2);
        bwlList.add(be1);
        bwlList.add(be2);
        bwlList.add(be1);
        bwlList.add(be2);
        bwlList.add(be1);
        bwlList.add(be2);
        bwlList.add(be1);
        bwlList.add(be2);
    }

    @Override
    protected void onResume() {
        sort();
        super.onResume();
    }

    public static void sort(){
        Collections.sort(BwlActivity.bwlList);
        BwlActivity.bwlAdapter.notifyDataSetChanged();
    }

}
