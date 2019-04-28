package com.example.remember.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.adapter.RcQAdapter;
import com.example.remember.adapter.RcUnqAdapter;
import com.example.remember.db.Rc_q;
import com.example.remember.db.Rc_unq;
import com.example.remember.listener.BtnListener;
import com.example.remember.listener.BtnLongListener;
import com.example.remember.listener.TouchListener;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.DateUtil;
import com.example.remember.util.FontManager;
import com.example.remember.util.MyApplication;
import com.example.remember.util.MyDialog;
import com.example.remember.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RcActivity extends AppCompatActivity {

    private float mPosX;
    private float mPosY;
    private float mCurPosX;
    private float mCurPosY;

    private TextView tvYear;
    private TextView tvMonth;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private List<Date> dateList;
    private List<String> list;

    private RecyclerView rv1;
    private RecyclerView rv2;
    private RecyclerView rv3;
    private RecyclerView rv4;
    private RecyclerView rv5;
    private RecyclerView rv6;
    private RecyclerView rv7;
    private RecyclerView rvUnq;

    public static List<Rc_q> rcqlist1;
    public static List<Rc_q> rcqlist2;
    public static List<Rc_q> rcqlist3;
    public static List<Rc_q> rcqlist4;
    public static List<Rc_q> rcqlist5;
    public static List<Rc_q> rcqlist6;
    public static List<Rc_q> rcqlist7;
    public static List<Rc_unq> rcUnqList;

    public static RcQAdapter adapter1;
    public static RcQAdapter adapter2;
    public static RcQAdapter adapter3;
    public static RcQAdapter adapter4;
    public static RcQAdapter adapter5;
    public static RcQAdapter adapter6;
    public static RcQAdapter adapter7;
    public static RcUnqAdapter adapterUnq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc);

        //初始化数据
        iniRcqList();
        iniRcUnqList();
        dateList = DateUtil.getWeek(new Date());

        TouchListener touchListener = new TouchListener();
        BtnListener listener = new BtnListener(this);
        BtnLongListener longListener = new BtnLongListener(this);
        View rcqView = this.getLayoutInflater().inflate(R.layout.dialog_rc_q, null);
        View rcqSetView = this.getLayoutInflater().inflate(R.layout.dialog_rc_q_set, null);
        View rcqAddView = this.getLayoutInflater().inflate(R.layout.dialog_rc_q_set, null);

        //控件实例获取
        tvYear = (TextView)findViewById(R.id.text_rc_year);
        tvMonth = (TextView)findViewById(R.id.text_rc_month);
        tv1 = (TextView)findViewById(R.id.text_rc_q1);
        tv2 = (TextView)findViewById(R.id.text_rc_q2);
        tv3 = (TextView)findViewById(R.id.text_rc_q3);
        tv4 = (TextView)findViewById(R.id.text_rc_q4);
        tv5 = (TextView)findViewById(R.id.text_rc_q5);
        tv6 = (TextView)findViewById(R.id.text_rc_q6);
        tv7 = (TextView)findViewById(R.id.text_rc_q7);
        rv1 = (RecyclerView) findViewById(R.id.recycler_rc_q1);
        rv2 = (RecyclerView) findViewById(R.id.recycler_rc_q2);
        rv3 = (RecyclerView) findViewById(R.id.recycler_rc_q3);
        rv4 = (RecyclerView) findViewById(R.id.recycler_rc_q4);
        rv5 = (RecyclerView) findViewById(R.id.recycler_rc_q5);
        rv6 = (RecyclerView) findViewById(R.id.recycler_rc_q6);
        rv7 = (RecyclerView) findViewById(R.id.recycler_rc_q7);
        rvUnq = (RecyclerView) findViewById(R.id.recycler_rc_unq);
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
        list = DateUtil.dateListToStrList(dateList);
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
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager6 = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager7 = new LinearLayoutManager(this);
        StaggeredGridLayoutManager layoutManagerUnq = new StaggeredGridLayoutManager(6,StaggeredGridLayoutManager.VERTICAL);

        rv1.setLayoutManager(layoutManager1);
        rv2.setLayoutManager(layoutManager2);
        rv3.setLayoutManager(layoutManager3);
        rv4.setLayoutManager(layoutManager4);
        rv5.setLayoutManager(layoutManager5);
        rv6.setLayoutManager(layoutManager6);
        rv7.setLayoutManager(layoutManager7);
        rvUnq.setLayoutManager(layoutManagerUnq);

        adapter1 = new RcQAdapter(rcqlist1);
        adapter2 = new RcQAdapter(rcqlist2);
        adapter3 = new RcQAdapter(rcqlist3);
        adapter4 = new RcQAdapter(rcqlist4);
        adapter5 = new RcQAdapter(rcqlist5);
        adapter6 = new RcQAdapter(rcqlist6);
        adapter7 = new RcQAdapter(rcqlist7);
        adapterUnq = new RcUnqAdapter(rcUnqList);

        rv1.setAdapter(adapter1);
        rv2.setAdapter(adapter2);
        rv3.setAdapter(adapter3);
        rv4.setAdapter(adapter4);
        rv5.setAdapter(adapter5);
        rv6.setAdapter(adapter6);
        rv7.setAdapter(adapter7);
        rvUnq.setAdapter(adapterUnq);

        MyDialog.rcqDialog = new MyDialog(this, rcqView, R.style.DialogTheme);
        MyDialog.rcqDialog_set = new MyDialog(this, rcqSetView, R.style.DialogTheme);
        MyDialog.rcqDialog_add = new MyDialog(this, rcqAddView, R.style.DialogTheme);
        MyDialog.rcqDialog.setCancelable(true);
        MyDialog.rcqDialog_set.setCancelable(true);
        MyDialog.rcqDialog_add.setCancelable(true);

    }

    private void iniRcqList() {
        rcqlist1 = new ArrayList<>();
        rcqlist2 = new ArrayList<>();
        rcqlist3 = new ArrayList<>();
        rcqlist4 = new ArrayList<>();
        rcqlist5 = new ArrayList<>();
        rcqlist6 = new ArrayList<>();
        rcqlist7 = new ArrayList<>();

        Rc_q rcq1 = new Rc_q("2019-4-8 15:03:59","标题111","备注111",ColorUtil.colors[5]);
        Rc_q rcq2 = new Rc_q("2019-4-8 21:35:03","标题222","备注222",ColorUtil.colors[6]);

        rcqlist1.add(rcq2);
        rcqlist1.add(rcq1);

        rcqlist2.add(rcq1);
        rcqlist2.add(rcq2);

        rcqlist3.add(rcq2);
        rcqlist3.add(rcq1);

        rcqlist4.add(rcq1);
        rcqlist4.add(rcq2);

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPosX = event.getX();
                mPosY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurPosX = event.getX();
                mCurPosY = event.getY();

                break;
            case MotionEvent.ACTION_UP:
                if (mCurPosX - mPosX < 0) {
                    dateList = DateUtil.getLatWeek(dateList.get(0));
                    tvYear.setText(DateUtil.getYear(dateList.get(0))+"年");
                    tvMonth.setText(StringUtil.numToStr(Integer.parseInt(DateUtil.getMonth(dateList.get(0))))+"月");
                    list = DateUtil.dateListToStrList(dateList);
                    tv1.setText(list.get(0));
                    tv2.setText(list.get(1));
                    tv3.setText(list.get(2));
                    tv4.setText(list.get(3));
                    tv5.setText(list.get(4));
                    tv6.setText(list.get(5));
                    tv7.setText(list.get(6));
                }
                if (mCurPosX - mPosX > 0) {
                    dateList = DateUtil.getPreWeek(dateList.get(0));
                    tvYear.setText(DateUtil.getYear(dateList.get(0))+"年");
                    tvMonth.setText(StringUtil.numToStr(Integer.parseInt(DateUtil.getMonth(dateList.get(0))))+"月");
                    list = DateUtil.dateListToStrList(dateList);
                    tv1.setText(list.get(0));
                    tv2.setText(list.get(1));
                    tv3.setText(list.get(2));
                    tv4.setText(list.get(3));
                    tv5.setText(list.get(4));
                    tv6.setText(list.get(5));
                    tv7.setText(list.get(6));
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public static void notifyDateChange(){
        Collections.sort(RcActivity.rcqlist1);
        Collections.sort(RcActivity.rcqlist2);
        Collections.sort(RcActivity.rcqlist3);
        Collections.sort(RcActivity.rcqlist4);
        Collections.sort(RcActivity.rcqlist5);
        Collections.sort(RcActivity.rcqlist6);
        Collections.sort(RcActivity.rcqlist7);
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();
        adapter4.notifyDataSetChanged();
        adapter5.notifyDataSetChanged();
        adapter6.notifyDataSetChanged();
        adapter7.notifyDataSetChanged();
    }

}
