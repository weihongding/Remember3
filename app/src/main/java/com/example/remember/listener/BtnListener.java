package com.example.remember.listener;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.activity.BwlDetailActivity;
import com.example.remember.activity.JlActivity;
import com.example.remember.activity.JlDetailActivity;
import com.example.remember.activity.RcActivity;
import com.example.remember.db.Bwl_event;
import com.example.remember.db.Jl;
import com.example.remember.db.Jl_sj_event;
import com.example.remember.db.Rc_q;
import com.example.remember.db.Rc_unq;
import com.example.remember.util.BaseActivity;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.DataUtil;
import com.example.remember.util.DateUtil;
import com.example.remember.util.MyApplication;
import com.example.remember.util.MyDialog;
import com.example.remember.util.ObjectUtil;
import com.example.remember.util.StringUtil;
import com.example.remember.util.ViewUtil;

import java.util.Collections;
import java.util.Date;

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

            //记录相关监听
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

            //设备相关监听
            case R.id.btn_sb_add:{
                Toast.makeText(MyApplication.getContext(), "点击了添加", Toast.LENGTH_SHORT).show();
                break;
            }

            //日程表相关监听
            case R.id.btn_rc_add: {

                EditText et_title = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_add_title);
                EditText et_des = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_add_des);
                EditText et_year = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_year);
                EditText et_month = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_month);
                EditText et_day = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_day);
                EditText et_H = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_H);
                EditText et_M = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_M);
                EditText et_S = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_S);
                Button btn_rcq_add_color = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_add_color);
                Button btn_rcq_add_addunq = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_add_addunq);
                Button btn_rcq_add_save = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_add_save);
                TextView tvLine = (TextView)MyDialog.rcqDialog_add.findViewById(R.id.line);

                et_title.setText("");
                et_des.setText("");
                et_year.setText(Integer.toString(DateUtil.getYear()));
                et_month.setText(Integer.toString(DateUtil.getMonth()));
                et_day.setText(Integer.toString(DateUtil.getDay()));
                et_H.setText(Integer.toString(DateUtil.getH()));
                et_M.setText(Integer.toString(DateUtil.getM()));
                et_S.setText(Integer.toString(DateUtil.getS()));

                ViewUtil.setViewColor(btn_rcq_add_color,ColorUtil.colors[0]);
                btn_rcq_add_addunq.setVisibility(View.VISIBLE);
                tvLine.setVisibility(View.VISIBLE);

                btn_rcq_add_color.setOnClickListener(this);
                btn_rcq_add_addunq.setOnClickListener(this);
                btn_rcq_add_save.setOnClickListener(this);
                MyDialog.rcqDialog_add.show();

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
                Rc_unq rc_unq = ObjectUtil.rcqToRcunq(Rc_q.chooseRcq);
                rc_unq.save();
                MyDialog.rcqDialog.hide();
                RcActivity.refreshUnq();
                Toast.makeText(MyApplication.getContext(), "点击了aaaa添加待定", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_q_del:{
                Rc_q.chooseRcq.delete();
                RcActivity.refresh();
                MyDialog.rcqDialog.hide();
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
                MyDialog.colorDialog_rcq_set.show();
                break;
            }
            case R.id.btn_rc_q_set_addunq:{
                Rc_unq rc_unq = ObjectUtil.rcqToRcunq(Rc_q.chooseRcq);
                if (ColorUtil.choose_color_rcq_set != null){
                    rc_unq.setColor(ColorUtil.choose_color_rcq_set);
                }
                rc_unq.save();
                RcActivity.refreshUnq();
                MyDialog.rcqDialog_set.hide();
                ColorUtil.choose_color_rcq_set = null;
                Toast.makeText(MyApplication.getContext(), "点击了添加待定", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_q_set_del:{
                Rc_q.chooseRcq.delete();
                MyDialog.rcqDialog_set.hide();
                ColorUtil.choose_color_rcq_set = null;
                Toast.makeText(MyApplication.getContext(), "点击了删除", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_q_set_save:{

                EditText et_title = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_set_title);
                EditText et_des = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_set_des);
                EditText et_year = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_time_set_year);
                EditText et_month = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_time_set_month);
                EditText et_day = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_time_set_day);
                EditText et_H = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_time_set_H);
                EditText et_M = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_time_set_M);
                EditText et_S = (EditText) MyDialog.rcqDialog_set.findViewById(R.id.edit_rc_q_time_set_S);

                String title = et_title.getText().toString();
                String des = et_des.getText().toString();
                String year = et_year.getText().toString();
                String month = et_month.getText().toString();
                String day = et_day.getText().toString();
                String H = et_H.getText().toString();
                String M = et_M.getText().toString();
                String S = et_S.getText().toString();
                String time = DateUtil.conDateStr(year,month,day,H,M,S);

                Rc_q.chooseRcq.setTitle(title);
                Rc_q.chooseRcq.setDes(des);
                Rc_q.chooseRcq.setTime(time);
                Rc_q.chooseRcq.setColor(ColorUtil.choose_color_rcq_set);
                Rc_q.chooseRcq.save();

                ColorUtil.choose_color_rcq_set=null;

                Toast.makeText(MyApplication.getContext(), "修改日程保存成功", Toast.LENGTH_SHORT).show();
                MyDialog.rcqDialog_set.hide();
                RcActivity.refresh();

                break;
            }
            case R.id.btn_rc_q_add_color:{
                MyDialog.colorDialog_rcq_add.show();
                break;
            }
            case R.id.btn_rc_q_add_addunq:{

                EditText et_title = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_add_title);
                EditText et_des = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_add_des);

                String title = et_title.getText().toString();
                String des = et_des.getText().toString();
                String color = ColorUtil.choose_color_rcq_add==null?ColorUtil.colors[0]:ColorUtil.choose_color_rcq_add;
                Rc_unq rcUnq = new Rc_unq(title,des,color);
                rcUnq.save();
                RcActivity.refreshUnq();
                Toast.makeText(MyApplication.getContext(), "点击了添加待定", Toast.LENGTH_SHORT).show();

                ColorUtil.choose_color_rcq_add = null;
                MyDialog.rcqDialog_add.hide();
                break;

            }
            case R.id.btn_rc_q_add_save: {

                EditText et_title = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_add_title);
                EditText et_des = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_add_des);
                EditText et_year = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_year);
                EditText et_month = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_month);
                EditText et_day = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_day);
                EditText et_H = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_H);
                EditText et_M = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_M);
                EditText et_S = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_S);

                String title = et_title.getText().toString();
                String des = et_des.getText().toString();
                String year = et_year.getText().toString();
                String month = et_month.getText().toString();
                String day = et_day.getText().toString();
                String H = et_H.getText().toString();
                String M = et_M.getText().toString();
                String S = et_S.getText().toString();
                String time = DateUtil.conDateStr(year, month, day, H, M, S);
                String color = ColorUtil.choose_color_rcq_add == null ? ColorUtil.colors[0] : ColorUtil.choose_color_rcq_add;

                Rc_q rcq = new Rc_q(time, title, des, color);
                rcq.save();

                ColorUtil.choose_color_rcq_add = null;
                Toast.makeText(MyApplication.getContext(), "新增日程成功", Toast.LENGTH_SHORT).show();
                MyDialog.rcqDialog_add.hide();
                RcActivity.refresh();

                break;

            }
            //待定日程查看
            case R.id.btn_rc_unq_addq:{

                Rc_q rcq = ObjectUtil.rcunqToRcq(Rc_unq.chooseRcUnq,DateUtil.dateToStr(new Date()));

                EditText et_title = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_add_title);
                EditText et_des = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_add_des);
                EditText et_year = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_year);
                EditText et_month = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_month);
                EditText et_day = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_day);
                EditText et_H = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_H);
                EditText et_M = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_M);
                EditText et_S = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_S);
                Button btn_rcq_add_color = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_add_color);
                Button btn_rcq_add_addunq = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_add_addunq);
                Button btn_rcq_add_save = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_add_save);
                TextView tv_line = (TextView)MyDialog.rcqDialog_add.findViewById(R.id.line);

                et_title.setText(rcq.getTitle());
                et_des.setText(rcq.getDes());
                et_year.setText(DateUtil.getYear(rcq.getTime()));
                et_month.setText(DateUtil.getMonth(rcq.getTime()));
                et_day.setText(DateUtil.getDay(rcq.getTime()));
                et_H.setText(DateUtil.getH(rcq.getTime()));
                et_M.setText(DateUtil.getM(rcq.getTime()));
                et_S.setText(DateUtil.getS(rcq.getTime()));

                ViewUtil.setViewColor(btn_rcq_add_color,rcq.getColor());
                btn_rcq_add_addunq.setVisibility(View.GONE);
                tv_line.setVisibility(View.GONE);

                btn_rcq_add_color.setOnClickListener(this);
                btn_rcq_add_save.setOnClickListener(this);

                MyDialog.rcunqDialog.hide();
                MyDialog.rcqDialog_add.show();
                ColorUtil.choose_color_rcq_add = rcq.getColor();
                Toast.makeText(MyApplication.getContext(), "点击了添加日程", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_unq_del:{
                Rc_unq.chooseRcUnq.delete();
                Rc_unq.chooseRcUnq=null;
                MyDialog.rcunqDialog.hide();
                RcActivity.refreshUnq();
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
                MyDialog.colorDialog_rcUnq_set.show();
                break;
            }
            case R.id.btn_rc_unq_set_addq:{

                Rc_q rcq = ObjectUtil.rcunqToRcq(Rc_unq.chooseRcUnq,DateUtil.dateToStr(new Date()));
                if (ColorUtil.choose_color_rcUnq_set!=null){
                    rcq.setColor(ColorUtil.choose_color_rcq_set);
                }

                EditText et_title = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_add_title);
                EditText et_des = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_add_des);
                EditText et_year = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_year);
                EditText et_month = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_month);
                EditText et_day = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_day);
                EditText et_H = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_H);
                EditText et_M = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_M);
                EditText et_S = (EditText) MyDialog.rcqDialog_add.findViewById(R.id.edit_rc_q_time_add_S);
                Button btn_rcq_add_color = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_add_color);
                Button btn_rcq_add_addunq = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_add_addunq);
                Button btn_rcq_add_save = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_add_save);
                TextView tv_line = (TextView)MyDialog.rcqDialog_add.findViewById(R.id.line);

                et_title.setText(rcq.getTitle());
                et_des.setText(rcq.getDes());
                et_year.setText(DateUtil.getYear(rcq.getTime()));
                et_month.setText(DateUtil.getMonth(rcq.getTime()));
                et_day.setText(DateUtil.getDay(rcq.getTime()));
                et_H.setText(DateUtil.getH(rcq.getTime()));
                et_M.setText(DateUtil.getM(rcq.getTime()));
                et_S.setText(DateUtil.getS(rcq.getTime()));

                ViewUtil.setViewColor(btn_rcq_add_color,rcq.getColor());
                btn_rcq_add_addunq.setVisibility(View.GONE);
                tv_line.setVisibility(View.GONE);

                btn_rcq_add_color.setOnClickListener(this);
                btn_rcq_add_save.setOnClickListener(this);

                MyDialog.rcunqDialog_set.hide();
                MyDialog.rcqDialog_add.show();
                ColorUtil.choose_color_rcq_add = rcq.getColor();
                Toast.makeText(MyApplication.getContext(), "点击了添加日程", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_unq_set_del:{
                Rc_unq.chooseRcUnq.delete();
                Rc_unq.chooseRcUnq = null;
                ColorUtil.choose_color_rcUnq_set = null;
                MyDialog.rcunqDialog_set.hide();
                RcActivity.refreshUnq();
                Toast.makeText(MyApplication.getContext(), "点击了删除", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_unq_set_save:{
                Rc_unq rcUnq = Rc_unq.chooseRcUnq;

                EditText et_title = (EditText) MyDialog.rcunqDialog_set.findViewById(R.id.edit_rc_unq_set_title);
                EditText et_des = (EditText) MyDialog.rcunqDialog_set.findViewById(R.id.edit_rc_unq_set_des);

                rcUnq.setTitle(et_title.getText().toString());
                rcUnq.setDes(et_des.getText().toString());
                if (ColorUtil.choose_color_rcq_set!=null){
                    rcUnq.setColor(ColorUtil.choose_color_rcq_set);
                }
                rcUnq.save();

                MyDialog.rcunqDialog_set.hide();

                Toast.makeText(MyApplication.getContext(), "点击了保存", Toast.LENGTH_SHORT).show();
                break;
            }

        }
    }

}
