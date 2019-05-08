package com.example.remember.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.remember.R;
import com.example.remember.adapter.ColorAdapter;
import com.example.remember.adapter.JlAdapter_sj;
import com.example.remember.adapter.JlAdapter_zb;
import com.example.remember.db.Jl;
import com.example.remember.listener.BtnListener;
import com.example.remember.util.BaseActivity;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.DbUtil;
import com.example.remember.util.MyDialog;
import com.example.remember.util.StringUtil;
import com.example.remember.util.ViewUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JlDetailActivity extends BaseActivity {

    public static List list;
    public static RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jl_detail);

        //初始化数据和适配器
        requestDate(Jl.choose_jl.getType(),Jl.choose_jl.getId());
        iniAdapter(Jl.choose_jl.getType());

        View layoutText = (View) findViewById(R.id.layout_jl_detail);
        View layoutSet = (View) findViewById(R.id.layout_jl_detail_set);
        Button btn_add = (Button) findViewById(R.id.btn_jl_detail_add);
        Button btn_set = (Button) findViewById(R.id.btn_jl_detail_set);
        Button btn_color = (Button) findViewById(R.id.btn_jl_detail_color);
        Button btn_save = (Button) findViewById(R.id.btn_jl_detail_save);
        TextView tv_title = (TextView) findViewById(R.id.text_jl_detail_title);
        TextView tv_type1 = (TextView) findViewById(R.id.text_jl_detail_type1);
        TextView tv_color = (TextView) findViewById(R.id.text_jl_detail_color);
        TextView tv_des = (TextView) findViewById(R.id.text_jl_detail_des);
        TextView tv_type2 = (TextView) findViewById(R.id.text_jl_detail_type2);
        EditText et_title = (EditText) findViewById(R.id.edit_jl_detail_title);
        EditText et_des = (EditText) findViewById(R.id.edit_jl_detail_des);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_jl_detail);

        //初始化相关组件
        View itemView = this.getLayoutInflater().inflate(R.layout.dialog_jl_detail_item,null);
        MyDialog.jlDialog_item=new MyDialog(this,itemView,R.style.DialogTheme);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        View colorView = this.getLayoutInflater().inflate(R.layout.choose_color,null);
        MyDialog.colorDialog_jl=new MyDialog(this,colorView,R.style.DialogTheme);
        StaggeredGridLayoutManager colorLayoutManager = new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL);
        RecyclerView recycler_color = (RecyclerView)MyDialog.colorDialog_jl.findViewById(R.id.recycler_color);
        recycler_color.setLayoutManager(colorLayoutManager);
        recycler_color.setAdapter(new ColorAdapter());

        //初始化样式
        btn_set.setTypeface(ViewUtil.getTypeface());
        btn_save.setTypeface(ViewUtil.getTypeface());
        ViewUtil.setViewColor(btn_color,Jl.choose_jl.getColor());
        ViewUtil.setViewColor(tv_color,Jl.choose_jl.getColor());
        layoutText.setVisibility(View.VISIBLE);
        layoutSet.setVisibility(View.GONE);

        //初始化内容
        tv_title.setText(Jl.choose_jl.getTitle());
        tv_des.setText(Jl.choose_jl.getDes());
        tv_type1.setText(Jl.choose_jl.getType());
        tv_type2.setText(Jl.choose_jl.getType());
        et_title.setText(Jl.choose_jl.getTitle());
        et_des.setText(Jl.choose_jl.getDes());

        //添加监听器和适配器
        btn_add.setOnClickListener(BtnListener.instance);
        btn_set.setOnClickListener(BtnListener.instance);
        btn_color.setOnClickListener(BtnListener.instance);
        btn_save.setOnClickListener(BtnListener.instance);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        sort();

    }

    @Override
    protected void onDestroy() {
        MyDialog.colorDialog_jl.dismiss();
        MyDialog.jlDialog_item.dismiss();
        ColorUtil.choose_color_jlDetail_set = null;
        super.onDestroy();
    }

    public static void requestDate(String type,int jlId){

        if (list == null){list = new ArrayList<>();}
        list = DbUtil.requestJlDetailList(type,jlId);

    }

    public static void iniAdapter(String type){

        if (type.equals(StringUtil.eventType_sj)){
            adapter = new JlAdapter_sj(list);
        }else if (type.equals(StringUtil.eventType_zb)){
            adapter = new JlAdapter_zb(list);
        }

    }

    @Override
    protected void onResume() {
        refresh();
        super.onResume();
    }

    public static void sort(){
        Collections.sort(list);
        adapter.notifyDataSetChanged();
    }

    public static void refresh(){
        list.clear();
        list.addAll(DbUtil.requestJlDetailList(Jl.choose_jl.getType(),Jl.choose_jl.getId()));
        sort();
    }

}
