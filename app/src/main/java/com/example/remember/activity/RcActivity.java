package com.example.remember.activity;

import android.graphics.Typeface;
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
import com.example.remember.entity.Rc_q;
import com.example.remember.entity.Rc_unq;
import com.example.remember.listener.RcBtnListener;
import com.example.remember.listener.RcBtnLongListener;
import com.example.remember.util.DateUtil;

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
        if (rcqlist1==null){
            iniRcqList1();//初始化数据
        }
        if (rcqlist2==null){
            iniRcqList2();//初始化数据
        }
        if (rcUnqList==null){
            iniRcUnqList();//初始化数据
        }
        List<String> list = DateUtil.getWeek(new Date());

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        RcBtnListener listener = new RcBtnListener(this);
        RcBtnLongListener longListener = new RcBtnLongListener(this);

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
        btn_history.setTypeface(font);
        btn_change.setTypeface(font);
        btn_add.setOnClickListener(listener);
        btn_history.setOnClickListener(listener);
        btn_change.setOnClickListener(listener);
        btn_add.setOnLongClickListener(longListener);
        tv1.setText(list.get(0));
        tv2.setText(list.get(1));
        tv3.setText(list.get(2));
        tv4.setText(list.get(3));
        tv5.setText(list.get(4));
        tv6.setText(list.get(5));
        tv7.setText(list.get(6));

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
