package com.example.remember.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import com.example.remember.R;
import com.example.remember.adapter.JlAdapter;
import com.example.remember.db.Jl;
import com.example.remember.listener.BtnListener;
import com.example.remember.util.BaseActivity;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.MyDialog;
import com.example.remember.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JlActivity extends BaseActivity {

    public static List<Jl> jlList;
    public static JlAdapter jlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jl);

        //初始化数据
        iniJlList();

        View jlView = this.getLayoutInflater().inflate(R.layout.dialog_jl_add, null);
        MyDialog.jlDialog_add=new MyDialog(this,jlView,R.style.DialogTheme);

        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_jl);
        Button btn_add = (Button)findViewById(R.id.btn_jl_add);
        Button btn_add_sj = (Button)MyDialog.jlDialog_add.findViewById(R.id.btn_jl_add_sj);
        Button btn_add_zb = (Button)MyDialog.jlDialog_add.findViewById(R.id.btn_jl_add_zb);

        btn_add.setOnClickListener(BtnListener.instance);
        btn_add_sj.setOnClickListener(BtnListener.instance);
        btn_add_zb.setOnClickListener(BtnListener.instance);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        jlAdapter = new JlAdapter(jlList);
        rv.setAdapter(jlAdapter);

        sort();

    }

    @Override
    protected void onDestroy() {
        MyDialog.jlDialog_add.dismiss();
        super.onDestroy();
    }

    private void iniJlList(){
        jlList = new ArrayList<>();
        Jl jl1 = new Jl(StringUtil.eventType_sj,"吃感冒药","无", ColorUtil.colors[2],"2019-4-19 14:23:32");
        Jl jl2 = new Jl(StringUtil.eventType_zb,"体重","减肥",ColorUtil.colors[3],"2019-4-20 14:23:32");
        jlList.add(jl1);
        jlList.add(jl2);
    }

    public static void sort(){
        Collections.sort(jlList);
        jlAdapter.notifyDataSetChanged();
    }


}
