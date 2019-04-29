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

import com.example.remember.R;
import com.example.remember.db.Rc_unq;
import com.example.remember.listener.BtnListener;
import com.example.remember.util.DateUtil;
import com.example.remember.util.MyDialog;

import java.util.List;

public class RcUnqAdapter extends RecyclerView.Adapter<RcUnqAdapter.ViewHolder> {

    private List<Rc_unq> mRcUnqList;

    public RcUnqAdapter(List<Rc_unq> rcUnqList){
        this.mRcUnqList = rcUnqList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_unq_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rc_unq rcUnq = mRcUnqList.get(position);
        final String title = rcUnq.getTitle();
        final String des = rcUnq.getDes();
        final String color = rcUnq.getColor();
        GradientDrawable p1 = (GradientDrawable) holder.rcUnq.getBackground();
        p1.setColor(Color.parseColor(color));
        holder.rcUnq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rc_unq.chooseRcUnq = new Rc_unq(title,des,color);//保存一个备份
//                Rc_unq.chooseRcUnq.setId(id);

                TextView tv_title = (TextView) MyDialog.rcunqDialog.findViewById(R.id.text_rc_unq_title);
                TextView tv_des = (TextView)MyDialog.rcunqDialog.findViewById(R.id.text_rc_unq_des);
                TextView tv_color = (TextView)MyDialog.rcunqDialog.findViewById(R.id.text_rc_unq_color);
                Button btn_rcUnq_addq = (Button)MyDialog.rcunqDialog.findViewById(R.id.btn_rc_unq_addq);
                Button btn_rcUnq_del = (Button)MyDialog.rcunqDialog.findViewById(R.id.btn_rc_unq_del);
                Button btn_rcUnq_save = (Button)MyDialog.rcunqDialog.findViewById(R.id.btn_rc_unq_edit);

                btn_rcUnq_addq.setOnClickListener(BtnListener.instance);
                btn_rcUnq_del.setOnClickListener(BtnListener.instance);
                btn_rcUnq_save.setOnClickListener(BtnListener.instance);

                tv_title.setText(title);
                tv_des.setText(des);
                GradientDrawable p = (GradientDrawable) tv_color.getBackground();
                p.setColor(Color.parseColor(color));
                MyDialog.rcunqDialog.show();

            }
        });
        holder.rcUnq.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Rc_unq.chooseRcUnq = new Rc_unq(title,des,color);//保存一个备份
//                Rc_unq.chooseRcUnq.setId(id);

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

                GradientDrawable p = (GradientDrawable) btn_rcUnq_set_color.getBackground();
                p.setColor(Color.parseColor(color));

                MyDialog.rcunqDialog_set.show();
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return mRcUnqList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rcUnq;

        public ViewHolder(@NonNull View view) {
            super(view);
            rcUnq = (TextView)view.findViewById(R.id.text_rc_unq_item);
        }
    }

}
