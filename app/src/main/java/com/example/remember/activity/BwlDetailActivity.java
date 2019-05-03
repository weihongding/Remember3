package com.example.remember.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.remember.R;
import com.example.remember.adapter.ColorAdapter;
import com.example.remember.db.Bwl_event;
import com.example.remember.listener.BtnListener;
import com.example.remember.util.BaseActivity;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.MyDialog;
import com.example.remember.util.ViewUtil;

public class BwlDetailActivity extends BaseActivity {

//    private Bwl_event mBe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bwl_detail);

//        mBe = (Bwl_event) getIntent().getSerializableExtra("be");

        Button btn_color = (Button)findViewById(R.id.btn_bwl_color);
        Button btn_save = (Button)findViewById(R.id.btn_bwl_save);
        EditText et_content = (EditText)findViewById(R.id.edit_bwl_content);
        TextView tv_time = (TextView)findViewById(R.id.text_bwl_time);

        ViewUtil.setViewColor(btn_color,Bwl_event.choose_be.getColor());
        btn_save.setTypeface(ViewUtil.getTypeface());
        et_content.setText(Bwl_event.choose_be.getContent());
        tv_time.setText(Bwl_event.choose_be.getTime());

        btn_color.setOnClickListener(BtnListener.instance);
        btn_save.setOnClickListener(BtnListener.instance);

        View colorView = this.getLayoutInflater().inflate(R.layout.choose_color,null);
        MyDialog.colorDialog_bwl=new MyDialog(this,colorView,R.style.DialogTheme);
        StaggeredGridLayoutManager colorLayoutManager = new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL);
        RecyclerView recycler_color = (RecyclerView)MyDialog.colorDialog_bwl.findViewById(R.id.recycler_color);
        recycler_color.setLayoutManager(colorLayoutManager);
        recycler_color.setAdapter(new ColorAdapter());

    }

    @Override
    protected void onDestroy() {
        MyDialog.colorDialog_bwl.dismiss();
        Bwl_event.choose_be = null;
        ColorUtil.choose_color_bwl = null;
        super.onDestroy();
    }
}
