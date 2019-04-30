package com.example.remember.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.db.Jl;
import com.example.remember.util.ViewUtil;
import com.example.remember.util.MyApplication;
import com.example.remember.util.StringUtil;

import java.util.List;

public class JlAdapter extends RecyclerView.Adapter<JlAdapter.ViewHolder> {

    private List<Jl> mJlList;

    public JlAdapter(List<Jl> jlList){
        this.mJlList = jlList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jl_item,parent,false);
        JlAdapter.ViewHolder holder = new JlAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Jl jl = mJlList.get(position);
//        int id = jl.getId();
        String title = jl.getTitle();
        String type = jl.getType();
        String des = jl.getDes();
        String finTime = jl.getFinTime();
        final String color = jl.getColor();
        holder.tv_type.setText(type);
        holder.tv_title.setText(title);
        holder.tv_time.setText(finTime);

        if (type.equals(StringUtil.eventType_sj)){//记录类型为事件时添加监听器
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MyApplication.getContext(), "点击了整个事件Item", Toast.LENGTH_SHORT).show();
                }
            });
            holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(MyApplication.getContext(), "长按了整个事件Item", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }else if (type.equals(StringUtil.eventType_zb)){//记录类型为指标时添加监听器
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MyApplication.getContext(), "点击了整个指标Item", Toast.LENGTH_SHORT).show();
                }
            });
            holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(MyApplication.getContext(), "长按了整个指标Item", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

        ViewUtil.setViewColor(holder.layout,color);
    }

    @Override
    public int getItemCount() {
        return mJlList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView tv_title;
        TextView tv_time;
        TextView tv_type;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = (LinearLayout)view.findViewById(R.id.layout_jl_item);
            tv_title = (TextView)view.findViewById(R.id.text_jl_item_title);
            tv_time = (TextView)view.findViewById(R.id.text_jl_item_time);
            tv_type = (TextView)view.findViewById(R.id.text_jl_item_type);
        }
    }

}
