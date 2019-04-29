package com.example.remember.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.db.Rc_q;
import com.example.remember.listener.BtnListener;
import com.example.remember.util.DateUtil;
import com.example.remember.util.MyApplication;
import com.example.remember.util.MyDialog;

import java.util.List;

public class RcQAdapter extends RecyclerView.Adapter<RcQAdapter.ViewHolder> {

    private List<Rc_q> mRcqList;

    public RcQAdapter(List<Rc_q> rcQList){
        this.mRcqList = rcQList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_q_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rc_q rcq = mRcqList.get(position);
//        final int id = rcq.getId();
        final String des = rcq.getDes();
        final String time = rcq.getTime();
        final String title = rcq.getTitle();
        final String color = rcq.getColor();
        GradientDrawable p = (GradientDrawable) holder.rcTime.getBackground();
        p.setColor(Color.parseColor(color));

        holder.rcTime.setText(DateUtil.getH(time)+":"+DateUtil.getM(time));
        holder.rcTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rc_q.chooseRcq = new Rc_q(time,title,des,color);//保存一个备份
//                Rc_q.chooseRcq.setId(id);

                TextView tv_title = (TextView)MyDialog.rcqDialog.findViewById(R.id.text_rc_q_title);
                TextView tv_des = (TextView)MyDialog.rcqDialog.findViewById(R.id.text_rc_q_des);
                TextView tv_time = (TextView)MyDialog.rcqDialog.findViewById(R.id.text_rc_q_time);
                TextView tv_color = (TextView)MyDialog.rcqDialog.findViewById(R.id.text_rc_q_color);
                Button btn_rcq_addunq = (Button)MyDialog.rcqDialog.findViewById(R.id.btn_rc_q_addunq);
                Button btn_rcq_del = (Button)MyDialog.rcqDialog.findViewById(R.id.btn_rc_q_del);
                Button btn_rcq_save = (Button)MyDialog.rcqDialog.findViewById(R.id.btn_rc_q_edit);

                btn_rcq_addunq.setOnClickListener(BtnListener.instance);
                btn_rcq_del.setOnClickListener(BtnListener.instance);
                btn_rcq_save.setOnClickListener(BtnListener.instance);

                tv_title.setText(title);
                tv_des.setText(des);
                tv_time.setText(time);
                GradientDrawable p = (GradientDrawable) tv_color.getBackground();
                p.setColor(Color.parseColor(color));
                MyDialog.rcqDialog.show();
            }
        });
        holder.rcTime.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Rc_q.chooseRcq = new Rc_q(time,title,des,color);//保存一个备份
//                Rc_q.chooseRcq.setId(id);

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
                Button btn_rcq_set_del = (Button)MyDialog.rcqDialog_set.findViewById(R.id.btn_rc_q_set_del);
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
                btn_rcq_set_del.setOnClickListener(BtnListener.instance);
                btn_rcq_set_save.setOnClickListener(BtnListener.instance);

                GradientDrawable p = (GradientDrawable) btn_rcq_set_color.getBackground();
                p.setColor(Color.parseColor(color));

                MyDialog.rcqDialog_set.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRcqList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rcTime;

        public ViewHolder(@NonNull View view) {
            super(view);
            rcTime = (TextView)view.findViewById(R.id.text_rc_q_item_time);
        }
    }

}
