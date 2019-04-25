package com.example.remember.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.remember.R;
import com.example.remember.adapter.RcQAdapter;
import com.example.remember.adapter.RcUnqAdapter;
import com.example.remember.db.Rc_q;
import com.example.remember.db.Rc_unq;
import com.example.remember.listener.BtnListener;
import com.example.remember.listener.BtnLongListener;
import com.example.remember.util.DateUtil;
import com.example.remember.util.FontManager;
import com.example.remember.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RcActivity extends AppCompatActivity {

    public static List<Rc_q> rcqlist1;
    public static List<Rc_q> rcqlist2 ;
    public static List<Rc_unq> rcUnqList;

    public static RcQAdapter adapter1;
    public static RcQAdapter adapter2;
    public static RcUnqAdapter adapterUnq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc);

        //初始化数据
        iniRcqList1();
        iniRcqList2();
        iniRcUnqList();
        List<String> list = DateUtil.getWeek(new Date());

        BtnListener listener = new BtnListener(this);
        BtnLongListener longListener = new BtnLongListener(this);

        //控件实例获取
        TextView tvYear = (TextView)findViewById(R.id.text_rc_year);
        TextView tvMonth = (TextView)findViewById(R.id.text_rc_month);
        TextView tv1 = (TextView)findViewById(R.id.text_rc_q1);
        TextView tv2 = (TextView)findViewById(R.id.text_rc_q2);
        TextView tv3 = (TextView)findViewById(R.id.text_rc_q3);
        TextView tv4 = (TextView)findViewById(R.id.text_rc_q4);
        TextView tv5 = (TextView)findViewById(R.id.text_rc_q5);
        TextView tv6 = (TextView)findViewById(R.id.text_rc_q6);
        TextView tv7 = (TextView)findViewById(R.id.text_rc_q7);
        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.recycler_rc_q1);
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recycler_rc_q2);
        RecyclerView recyclerViewUnq = (RecyclerView) findViewById(R.id.recycler_rc_unq);
        Button btn_add = (Button) findViewById(R.id.btn_rc_add);
        Button btn_history = (Button) findViewById(R.id.btn_rc_history);
        Button btn_change = (Button) findViewById(R.id.btn_rc_change);

        //设置字体、监听器
        btn_history.setTypeface(FontManager.font);
        btn_change.setTypeface(FontManager.font);
        btn_add.setOnClickListener(listener);
        btn_history.setOnClickListener(listener);
        btn_change.setOnClickListener(listener);
        btn_add.setOnLongClickListener(longListener);

        //设置文本内容
        tvYear.setText(DateUtil.getYear()+"年");
        tvMonth.setText(StringUtil.numToStr(DateUtil.getMonth())+"月");
        tv1.setText(list.get(0));
        tv2.setText(list.get(1));
        tv3.setText(list.get(2));
        tv4.setText(list.get(3));
        tv5.setText(list.get(4));
        tv6.setText(list.get(5));
        tv7.setText(list.get(6));

        //列表初始化
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        StaggeredGridLayoutManager layoutManagerUnq = new StaggeredGridLayoutManager(6,StaggeredGridLayoutManager.VERTICAL);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerViewUnq.setLayoutManager(layoutManagerUnq);

        adapter1 = new RcQAdapter(rcqlist1);
        adapter2 = new RcQAdapter(rcqlist2);
        adapterUnq = new RcUnqAdapter(rcUnqList);

        recyclerView1.setAdapter(adapter1);
        recyclerView2.setAdapter(adapter2);
        recyclerViewUnq.setAdapter(adapterUnq);

    }

    private void iniRcqList1() {
        rcqlist1 = new ArrayList<>();
        Rc_q rcq1 = new Rc_q();
        rcq1.setStartTime("2019-4-8 15:03:59");
        rcq1.setColor("#ff0000");
        Rc_q rcq2 = new Rc_q();
        rcq2.setStartTime("2019-4-8 21:35:03");
        rcq2.setColor("#00ff00");
        rcqlist1.add(rcq2);
        rcqlist1.add(rcq1);
    }

    private void iniRcqList2() {
        rcqlist2 = new ArrayList<>();
        Rc_q rcq1 = new Rc_q();
        rcq1.setStartTime("2019-4-8 15:03:59");
        rcq1.setColor("#ff0000");
        Rc_q rcq2 = new Rc_q();
        rcq2.setStartTime("2019-4-8 21:35:03");
        rcq2.setColor("#00ff00");
        rcqlist2.add(rcq2);
        rcqlist2.add(rcq1);
    }

    private void iniRcUnqList() {
        rcUnqList = new ArrayList<>();
        Rc_unq rcUnq1 = new Rc_unq();
        rcUnq1.setColor("#ff0000");
        Rc_unq rcUnq2 = new Rc_unq();
        rcUnq2.setColor("#00ff00");
        rcUnqList.add(rcUnq1);
        rcUnqList.add(rcUnq2);
        rcUnqList.add(rcUnq1);
        rcUnqList.add(rcUnq2);
        rcUnqList.add(rcUnq1);
        rcUnqList.add(rcUnq2);
        rcUnqList.add(rcUnq1);
        rcUnqList.add(rcUnq2);
        rcUnqList.add(rcUnq1);
        rcUnqList.add(rcUnq2);
        rcUnqList.add(rcUnq1);
        rcUnqList.add(rcUnq2);
        rcUnqList.add(rcUnq1);
        rcUnqList.add(rcUnq2);
        rcUnqList.add(rcUnq1);
        rcUnqList.add(rcUnq2);
        rcUnqList.add(rcUnq1);
        rcUnqList.add(rcUnq2);
        rcUnqList.add(rcUnq1);
        rcUnqList.add(rcUnq2);
    }

}
