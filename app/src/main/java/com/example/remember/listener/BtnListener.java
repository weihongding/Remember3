package com.example.remember.listener;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.activity.BwlDetailActivity;
import com.example.remember.activity.JlActivity;
import com.example.remember.activity.JlDetailActivity;
import com.example.remember.db.Bwl_event;
import com.example.remember.db.Jl;
import com.example.remember.db.Jl_sj_event;
import com.example.remember.db.Rc_q;
import com.example.remember.db.Rc_unq;
import com.example.remember.util.BaseActivity;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.DateUtil;
import com.example.remember.util.MyApplication;
import com.example.remember.util.MyDialog;
import com.example.remember.util.StringUtil;
import com.example.remember.util.ViewUtil;

import java.util.Collections;

public class BtnListener implements View.OnClickListener  {

    public static BtnListener instance = new BtnListener();

    public BtnListener(){};

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //备忘录相关监听
            case R.id.btn_bwl_add:{
                Intent intent = new Intent(MyApplication.getContext(), BwlDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );//添加一个flag
                intent.putExtra("be",new Bwl_event());
                MyApplication.getContext().startActivity(intent);
                break;
            }
            case R.id.btn_bwl_color:{
                MyDialog.colorDialog_bwl.show();
                Toast.makeText(MyApplication.getContext(), "点击了选择颜色", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_bwl_save:{
                Toast.makeText(MyApplication.getContext(), "点击了保存", Toast.LENGTH_SHORT).show();
                break;
            }


            case R.id.btn_jl_add:{
                MyDialog.jlDialog_add.show();
                Toast.makeText(MyApplication.getContext(), "点击了添加", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_jl_add_sj:{
                Jl jl = new Jl(StringUtil.eventType_sj);
                JlActivity.jlList.add(jl);
                JlActivity.sort();
                MyDialog.jlDialog_add.hide();
                break;
            }
            case R.id.btn_jl_add_zb:{
                Jl jl = new Jl(StringUtil.eventType_zb);
                JlActivity.jlList.add(jl);
                JlActivity.sort();
                MyDialog.jlDialog_add.hide();
                break;
            }

            case R.id.btn_jl_detail_add:{
                Toast.makeText(MyApplication.getContext(), "点击了添加", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_jl_detail_color:{
                MyDialog.colorDialog_jl.show();
                Toast.makeText(MyApplication.getContext(), "点击了选择颜色", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_jl_detail_set:{

                View layoutText = (View) BaseActivity.getCurrentActivity().findViewById(R.id.layout_jl_detail);
                View layoutSet = (View) BaseActivity.getCurrentActivity().findViewById(R.id.layout_jl_detail_set);

                layoutText.setVisibility(View.GONE);
                layoutSet.setVisibility(View.VISIBLE);
                Toast.makeText(MyApplication.getContext(), "点击了设置", Toast.LENGTH_SHORT).show();
                break;

            }
            case R.id.btn_jl_detail_save:{
                View layoutText = (View) BaseActivity.getCurrentActivity().findViewById(R.id.layout_jl_detail);
                View layoutSet = (View) BaseActivity.getCurrentActivity().findViewById(R.id.layout_jl_detail_set);
                TextView tv_color = (TextView) BaseActivity.getCurrentActivity().findViewById(R.id.text_jl_detail_color);
                TextView tv_title = (TextView) BaseActivity.getCurrentActivity().findViewById(R.id.text_jl_detail_title);
                TextView tv_des = (TextView) BaseActivity.getCurrentActivity().findViewById(R.id.text_jl_detail_des);
                EditText et_title = (EditText) BaseActivity.getCurrentActivity().findViewById(R.id.edit_jl_detail_title);
                EditText et_des = (EditText) BaseActivity.getCurrentActivity().findViewById(R.id.edit_jl_detail_des);

                ViewUtil.setChooseColor(tv_color);
                tv_title.setText(et_title.getText());
                tv_des.setText(et_des.getText());

                layoutText.setVisibility(View.VISIBLE);
                layoutSet.setVisibility(View.GONE);
                Toast.makeText(MyApplication.getContext(), "点击了保存", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.btn_sb_add:{
                Toast.makeText(MyApplication.getContext(), "点击了添加", Toast.LENGTH_SHORT).show();
                break;
            }

            //日程表相关监听
            case R.id.btn_rc_add: {

                EditText et_title = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_set_title);
                EditText et_des = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_set_des);
                EditText et_year = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_year);
                EditText et_month = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_month);
                EditText et_day = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_day);
                EditText et_H = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_H);
                EditText et_M = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_M);
                EditText et_S = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_S);
                Button btn_rcq_add_color = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_color);
                Button btn_rcq_add_addunq = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_addunq);
                Button btn_rcq_add_del = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_del);
                Button btn_rcq_add_save = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_save);
                TextView line1 = (TextView)MyDialog.rcqDialog_add.findViewById(R.id.line_addunq);
                TextView line2 = (TextView)MyDialog.rcqDialog_add.findViewById(R.id.line_del);

                et_title.setText("");
                et_des.setText("");
                et_year.setText(Integer.toString(DateUtil.getYear()));
                et_month.setText(Integer.toString(DateUtil.getMonth()));
                et_day.setText(Integer.toString(DateUtil.getDay()));
                et_H.setText(Integer.toString(DateUtil.getH()));
                et_M.setText(Integer.toString(DateUtil.getM()));
                et_S.setText(Integer.toString(DateUtil.getS()));

                ViewUtil.setViewColor(btn_rcq_add_color,ColorUtil.colors[0]);

                btn_rcq_add_color.setOnClickListener(this);
                btn_rcq_add_addunq.setOnClickListener(this);
                btn_rcq_add_addunq.setVisibility(View.VISIBLE);
                btn_rcq_add_del.setVisibility(View.GONE);
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.GONE);
                btn_rcq_add_save.setOnClickListener(this);
                MyDialog.rcqDialog_add.show();

//                Rc_q rcq = new Rc_q("2019-4-8 15:03:59","标题333","备注333", ColorUtil.colors[7]);
//                RcActivity.rcqlist1.add(rcq);
//                RcActivity.rcqlist2.add(rcq);
//                RcActivity.notifyDateChange();
            }
            case R.id.btn_rc_change: {
                View layout_rc_add = (View) BaseActivity.getCurrentActivity().findViewById(R.id.layout_rc_add);
                View layout_rc_unq = (View) BaseActivity.getCurrentActivity().findViewById(R.id.layout_rc_unq);
                layout_rc_add.setVisibility(View.VISIBLE);
                layout_rc_unq.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_rc_history: {
                Toast.makeText(MyApplication.getContext(), "点击了同步", Toast.LENGTH_SHORT).show();
                break;
            }

            //确定日程查看
            case R.id.btn_rc_q_addunq:{
                Toast.makeText(MyApplication.getContext(), "点击了添加待定", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_q_del:{
                Toast.makeText(MyApplication.getContext(), "点击了删除", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_q_edit:{

                String title = Rc_q.chooseRcq.getTitle();
                String des = Rc_q.chooseRcq.getDes();
                String time = Rc_q.chooseRcq.getTime();
                String color = Rc_q.chooseRcq.getColor();

                EditText et_title = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_set_title);
                EditText et_des = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_set_des);
                EditText et_year = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_time_set_year);
                EditText et_month = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_time_set_month);
                EditText et_day = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_time_set_day);
                EditText et_H = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_time_set_H);
                EditText et_M = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_time_set_M);
                EditText et_S = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_time_set_S);
                Button btn_rcq_set_color = (Button)MyDialog.rcqDialog_set.findViewById(R.id.btn_rc_q_set_color);
                Button btn_rcq_set_addunq = (Button)MyDialog.rcqDialog_set.findViewById(R.id.btn_rc_q_set_addunq);
                Button btn_rcq_set_save = (Button)MyDialog.rcqDialog_set.findViewById(R.id.btn_rc_q_set_save);

                et_title.setText(title);
                et_des.setText(des);
                et_year.setText(DateUtil.getYear(time));
                et_month.setText(DateUtil.getMonth(time));
                et_day.setText(DateUtil.getDay(time));
                et_H.setText(DateUtil.getH(time));
                et_M.setText(DateUtil.getM(time));
                et_S.setText(DateUtil.getS(time));
                btn_rcq_set_color.setOnClickListener(BtnListener.instance);
                btn_rcq_set_addunq.setOnClickListener(BtnListener.instance);
                btn_rcq_set_save.setOnClickListener(BtnListener.instance);

                ViewUtil.setViewColor(btn_rcq_set_color,color);

                MyDialog.rcqDialog.hide();
                MyDialog.rcqDialog_set.show();
                break;

            }
            //确定日程编辑\添加
            case R.id.btn_rc_q_set_color:{
                MyDialog.colorDialog_rc.show();
                if (MyDialog.rcqDialog_set.isShowing()){
                    Toast.makeText(MyApplication.getContext(), "日程设置开启中", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.btn_rc_q_set_addunq:{
                Toast.makeText(MyApplication.getContext(), "点击了添加待定", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_q_set_del:{
                Toast.makeText(MyApplication.getContext(), "点击了删除", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_q_set_save:{
                Toast.makeText(MyApplication.getContext(), "点击了保存", Toast.LENGTH_SHORT).show();
                break;
            }
            //待定日程查看
            case R.id.btn_rc_unq_addq:{

                String title = Rc_unq.chooseRcUnq.getTitle();
                String des = Rc_unq.chooseRcUnq.getDes();
                String color = Rc_unq.chooseRcUnq.getColor();

                EditText et_title = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_set_title);
                EditText et_des = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_set_des);
                EditText et_year = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_year);
                EditText et_month = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_month);
                EditText et_day = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_day);
                EditText et_H = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_H);
                EditText et_M = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_M);
                EditText et_S = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_S);
                Button btn_rcq_add_color = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_color);
                Button btn_rcq_add_addunq = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_addunq);
                Button btn_rcq_add_del = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_del);
                Button btn_rcq_add_save = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_save);
                TextView line2 = (TextView)MyDialog.rcqDialog_add.findViewById(R.id.line_addunq);
                TextView line1 = (TextView)MyDialog.rcqDialog_add.findViewById(R.id.line_del);

                et_title.setText(title);
                et_des.setText(des);
                et_year.setText(Integer.toString(DateUtil.getYear()));
                et_month.setText(Integer.toString(DateUtil.getMonth()));
                et_day.setText(Integer.toString(DateUtil.getDay()));
                et_H.setText(Integer.toString(DateUtil.getH()));
                et_M.setText(Integer.toString(DateUtil.getM()));
                et_S.setText(Integer.toString(DateUtil.getS()));

                ViewUtil.setViewColor(btn_rcq_add_color,color);

                btn_rcq_add_color.setOnClickListener(this);
                btn_rcq_add_addunq.setVisibility(View.GONE);
                btn_rcq_add_del.setVisibility(View.GONE);
                line1.setVisibility(View.GONE);
                line2.setVisibility(View.GONE);
                btn_rcq_add_save.setOnClickListener(this);

                MyDialog.rcunqDialog.hide();
                MyDialog.rcqDialog_add.show();
                Toast.makeText(MyApplication.getContext(), "点击了添加日程", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_unq_del:{
                Toast.makeText(MyApplication.getContext(), "点击了删除", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_unq_edit:{

                String title = Rc_unq.chooseRcUnq.getTitle();
                String des = Rc_unq.chooseRcUnq.getDes();
                String color = Rc_unq.chooseRcUnq.getColor();

                EditText et_title = (EditText) MyDialog.rcunqDialog_set.findViewById(R.id.edit_rc_unq_set_title);
                EditText et_des = (EditText) MyDialog.rcunqDialog_set.findViewById(R.id.edit_rc_unq_set_des);
                Button btn_rcUnq_set_color = (Button)MyDialog.rcunqDialog_set.findViewById(R.id.btn_rc_unq_set_color);
                Button btn_rcUnq_set_addunq = (Button)MyDialog.rcunqDialog_set.findViewById(R.id.btn_rc_unq_set_addq);
                Button btn_rcUnq_set_del = (Button)MyDialog.rcunqDialog_set.findViewById(R.id.btn_rc_unq_set_del);
                Button btn_rcUnq_set_save = (Button)MyDialog.rcunqDialog_set.findViewById(R.id.btn_rc_unq_set_save);

                et_title.setText(title);
                et_des.setText(des);
                btn_rcUnq_set_color.setOnClickListener(BtnListener.instance);
                btn_rcUnq_set_addunq.setOnClickListener(BtnListener.instance);
                btn_rcUnq_set_del.setOnClickListener(BtnListener.instance);
                btn_rcUnq_set_save.setOnClickListener(BtnListener.instance);

                ViewUtil.setViewColor(btn_rcUnq_set_color,color);

                MyDialog.rcunqDialog.hide();
                MyDialog.rcunqDialog_set.show();
                break;

            }
            //待定日程编辑
            case R.id.btn_rc_unq_set_color:{
                MyDialog.colorDialog_rc.show();
                break;
            }
            case R.id.btn_rc_unq_set_addq:{

                String title = Rc_unq.chooseRcUnq.getTitle();
                String des = Rc_unq.chooseRcUnq.getDes();
                String color = Rc_unq.chooseRcUnq.getColor();

                EditText et_title = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_set_title);
                EditText et_des = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_set_des);
                EditText et_year = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_year);
                EditText et_month = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_month);
                EditText et_day = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_day);
                EditText et_H = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_H);
                EditText et_M = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_M);
                EditText et_S = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_set_S);
                Button btn_rcq_add_color = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_color);
                Button btn_rcq_add_addunq = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_addunq);
                Button btn_rcq_add_del = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_del);
                Button btn_rcq_add_save = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_save);
                TextView line2 = (TextView)MyDialog.rcqDialog_add.findViewById(R.id.line_addunq);
                TextView line1 = (TextView)MyDialog.rcqDialog_add.findViewById(R.id.line_del);

                et_title.setText(title);
                et_des.setText(des);
                et_year.setText(Integer.toString(DateUtil.getYear()));
                et_month.setText(Integer.toString(DateUtil.getMonth()));
                et_day.setText(Integer.toString(DateUtil.getDay()));
                et_H.setText(Integer.toString(DateUtil.getH()));
                et_M.setText(Integer.toString(DateUtil.getM()));
                et_S.setText(Integer.toString(DateUtil.getS()));

                ViewUtil.setViewColor(btn_rcq_add_color,color);

                btn_rcq_add_color.setOnClickListener(this);
                btn_rcq_add_addunq.setVisibility(View.GONE);
                btn_rcq_add_del.setVisibility(View.GONE);
                line1.setVisibility(View.GONE);
                line2.setVisibility(View.GONE);
                btn_rcq_add_save.setOnClickListener(this);

                MyDialog.rcunqDialog_set.hide();
                MyDialog.rcqDialog_add.show();
                Toast.makeText(MyApplication.getContext(), "点击了添加日程", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_unq_set_del:{
                Toast.makeText(MyApplication.getContext(), "点击了删除", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_unq_set_save:{
                Toast.makeText(MyApplication.getContext(), "点击了保存", Toast.LENGTH_SHORT).show();
                break;
            }

        }
    }

}
