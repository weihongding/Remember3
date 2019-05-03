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
import com.example.remember.util.DbUtil;
import com.example.remember.util.MyDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BwlActivity extends BaseActivity {

    public static List<Bwl_event> bwlList;
    public static RecyclerView.Adapter bwlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bwl);

        View bwlView = this.getLayoutInflater().inflate(R.layout.dialog_bwl_long, null);
        MyDialog.bwlDialog_long=new MyDialog(this,bwlView,R.style.DialogTheme);

        //初始化数据
        iniBwlList();

        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_bwl);
        Button btn_add = (Button)findViewById(R.id.btn_bwl_add);

        btn_add.setOnClickListener(BtnListener.instance);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        bwlAdapter = new BwlAdapter(bwlList);
        rv.setAdapter(bwlAdapter);

        sort();

    }

    @Override
    protected void onResume() {
        refresh();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        MyDialog.bwlDialog_long.dismiss();
        super.onDestroy();
    }

    private static void iniBwlList(){

        if (bwlList == null){bwlList = new ArrayList<>();}
        bwlList.clear();
        bwlList.addAll(DbUtil.requestBwlEventList());

    }

    public static void sort(){
        Collections.sort(bwlList);
        bwlAdapter.notifyDataSetChanged();
    }

    public static void refresh(){
        iniBwlList();
        sort();
    }

}
