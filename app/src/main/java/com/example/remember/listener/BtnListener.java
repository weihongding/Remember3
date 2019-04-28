package com.example.remember.listener;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.activity.BwlActivity;
import com.example.remember.activity.RcActivity;
import com.example.remember.db.Rc_q;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.MyDialog;

import java.util.Collections;

public class BtnListener implements View.OnClickListener  {

    private Activity mActivity;

    public BtnListener(Activity activity){
        mActivity = activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                Button btn_rcq_add_save = (Button)MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_save);
                btn_rcq_add_color.setOnClickListener(this);
                btn_rcq_add_addunq.setOnClickListener(this);
                btn_rcq_add_save.setOnClickListener(this);
                MyDialog.rcqDialog_add.show();

//                Rc_q rcq = new Rc_q("2019-4-8 15:03:59","标题333","备注333", ColorUtil.colors[7]);
//                RcActivity.rcqlist1.add(rcq);
//                RcActivity.rcqlist2.add(rcq);
//                RcActivity.notifyDateChange();
            }
            case R.id.btn_rc_change: {
                View layout_rc_add = (View) mActivity.findViewById(R.id.layout_rc_add);
                View layout_rc_unq = (View) mActivity.findViewById(R.id.layout_rc_unq);
                layout_rc_add.setVisibility(View.VISIBLE);
                layout_rc_unq.setVisibility(View.GONE);
                break;
            }
            case R.id.btn_rc_history: {
                Toast.makeText(mActivity, "点击了同步", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_bwl_add:{
                Collections.sort(BwlActivity.bwlList);
                BwlActivity.bwlAdapter.notifyDataSetChanged();
                Toast.makeText(mActivity, "点击了添加", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_jl_add:{
                Toast.makeText(mActivity, "点击了添加", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_sb_add:{
                Toast.makeText(mActivity, "点击了添加", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_q_set_color:{
                Toast.makeText(mActivity, "点击了选择颜色", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_q_set_addunq:{
                Toast.makeText(mActivity, "点击了添加待定", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_rc_q_set_save:{
                Toast.makeText(mActivity, "点击了保存", Toast.LENGTH_SHORT).show();
                break;
            }

        }
    }

}
