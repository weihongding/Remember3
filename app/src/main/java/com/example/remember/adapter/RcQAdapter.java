package com.example.remember.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.db.Rc_q;
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
        final String des = rcq.getDes();
        final String time = rcq.getTime();
        final String title = rcq.getTitle();
        final String color = rcq.getColor();

        final TextView tv_title = (TextView)MyDialog.rcqDialog.findViewById(R.id.text_rc_q_title);
        final TextView tv_des = (TextView)MyDialog.rcqDialog.findViewById(R.id.text_rc_q_des);
        final TextView tv_time = (TextView)MyDialog.rcqDialog.findViewById(R.id.text_rc_q_time);
        final TextView tv_color = (TextView)MyDialog.rcqDialog.findViewById(R.id.text_rc_q_color);

        holder.rcTime.setText(DateUtil.getH(time)+":"+DateUtil.getM(time));
        holder.rcTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Toast.makeText(MyApplication.getContext(), "长按了RecyclerViewItem\n颜色为："+color+"\n标题为："+title, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        GradientDrawable p = (GradientDrawable) holder.rcTime.getBackground();
        p.setColor(Color.parseColor(color));
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
