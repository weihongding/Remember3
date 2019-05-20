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
import com.example.remember.db.Jl_sj_event;
import com.example.remember.listener.BtnListener;
import com.example.remember.util.BaseActivity;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.DataUtil;
import com.example.remember.util.DateUtil;
import com.example.remember.util.DbUtil;
import com.example.remember.util.MyDialog;
import com.example.remember.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class JlActivity extends BaseActivity {

    public static List<Jl> jlList;
    public static JlAdapter jlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jl);
//        putNewCyJl();
        //初始化数据
        iniJlList();

        View shareView = this.getLayoutInflater().inflate(R.layout.dialog_jl_share, null);
        MyDialog.jlDialog_share=new MyDialog(this,shareView,R.style.DialogTheme);

        View jlView = this.getLayoutInflater().inflate(R.layout.dialog_jl_add, null);
        MyDialog.jlDialog_add=new MyDialog(this,jlView,R.style.DialogTheme);

        View jlLongView = this.getLayoutInflater().inflate(R.layout.dialog_jl_long, null);
        MyDialog.jlDialog_long=new MyDialog(this,jlLongView,R.style.DialogTheme);

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
    protected void onResume() {
        refresh();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        MyDialog.jlDialog_add.dismiss();
        MyDialog.jlDialog_long.dismiss();
        MyDialog.jlDialog_share.dismiss();
        super.onDestroy();
    }

    private void iniJlList(){
        if (jlList == null){jlList = new ArrayList<>();}
        jlList = DbUtil.requestJlList();

    }

    public static void sort(){
        Collections.sort(jlList);
        jlAdapter.notifyDataSetChanged();
    }

    public static void refresh(){

        jlList.clear();
        jlList.addAll(DbUtil.requestJlList());
        sort();

    }

//    public static void putNewCyJl(){
//        Jl jl = new Jl(StringUtil.eventType_sj,"吃药","别嘌醇",ColorUtil.colors[3], DateUtil.dateToStr(new Date()));
//        jl.save();
//        int jid = DbUtil.requestLastJlId();
//        Jl_sj_event jse1 = new Jl_sj_event(jid,"2019-5-6 13:33:49");
//        Jl_sj_event jse2 = new Jl_sj_event(jid,"2019-5-8 13:17:06");
//        Jl_sj_event jse3 = new Jl_sj_event(jid,"2019-5-9 18:45:42");
//        Jl_sj_event jse4 = new Jl_sj_event(jid,"2019-5-11 12:11:45");
//        Jl_sj_event jse5 = new Jl_sj_event(jid,"2019-5-13 13:44:32");
//        Jl_sj_event jse6 = new Jl_sj_event(jid,"2019-5-15 19:31:52");
//        Jl_sj_event jse7 = new Jl_sj_event(jid,"2019-5-16 12:54:31");
//        Jl_sj_event jse8 = new Jl_sj_event(jid,"2019-5-18 18:32:41");
//        jse1.save();
//        jse2.save();
//        jse3.save();
//        jse4.save();
//        jse5.save();
//        jse6.save();
//        jse7.save();
//        jse8.save();
//    }

}
