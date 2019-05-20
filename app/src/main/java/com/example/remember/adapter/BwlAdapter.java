package com.example.remember.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.remember.R;
import com.example.remember.activity.BwlDetailActivity;
import com.example.remember.db.Bwl_event;
import com.example.remember.listener.BtnListener;
import com.example.remember.util.MyApplication;
import com.example.remember.util.MyDialog;
import com.example.remember.util.StringUtil;
import com.example.remember.util.ViewUtil;

import java.util.List;

public class BwlAdapter extends RecyclerView.Adapter<BwlAdapter.ViewHolder>{

    private List<Bwl_event> mBwlList;

    public BwlAdapter(List<Bwl_event> bwlList){
        this.mBwlList = bwlList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bwl_item,parent,false);
        BwlAdapter.ViewHolder holder = new BwlAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Bwl_event be = mBwlList.get(position);
        String content = StringUtil.getContent_bwl(be.getContent());
        String time = be.getTime();
        final String color = be.getColor();
        holder.tv_content.setText(content);
        holder.tv_time.setText(time);
        ViewUtil.setViewColor(holder.layout,color);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bwl_event.choose_be = be;
                Intent intent = new Intent(MyApplication.getContext(), BwlDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );//添加一个flag
                MyApplication.getContext().startActivity(intent);
            }
        });
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Bwl_event.choose_be = be;
                MyDialog.bwlDialog_long.show();
                Button btn_del = (Button)MyDialog.bwlDialog_long.findViewById(R.id.btn_bwl_long_del);
                Button btn_share = (Button)MyDialog.bwlDialog_long.findViewById(R.id.btn_bwl_long_share);
                btn_del.setOnClickListener(BtnListener.instance);
                btn_share.setOnClickListener(BtnListener.instance);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBwlList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView tv_content;
        TextView tv_time;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = (LinearLayout)view.findViewById(R.id.layout_bwl_item);
            tv_content = (TextView)view.findViewById(R.id.text_bwl_item_content);
            tv_time = (TextView)view.findViewById(R.id.text_bwl_item_time);
        }
    }



}
