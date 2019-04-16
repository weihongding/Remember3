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
import com.example.remember.entity.Rc_q;
import com.example.remember.util.DateUtil;
import com.example.remember.util.MyApplication;

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
        String date = rcq.getStartTime();
        final String color = rcq.getColor();
        holder.rcStartTime.setText(DateUtil.getH(date)+":"+DateUtil.getM(date));
        holder.rcStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApplication.getContext(), "点击了RecyclerViewItem，颜色为"+color, Toast.LENGTH_SHORT).show();
            }
        });
        holder.rcStartTime.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MyApplication.getContext(), "长按了RecyclerViewItem，颜色为"+color, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        GradientDrawable p = (GradientDrawable) holder.rcStartTime.getBackground();
        p.setColor(Color.parseColor(color));
    }

    @Override
    public int getItemCount() {
        return mRcqList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rcStartTime;

        public ViewHolder(@NonNull View view) {
            super(view);
            rcStartTime = (TextView)view.findViewById(R.id.text_rc_q_item_time);
        }
    }

}
