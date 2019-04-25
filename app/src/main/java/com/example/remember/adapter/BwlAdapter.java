package com.example.remember.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.db.Bwl_event;
import com.example.remember.util.MyApplication;
import com.example.remember.util.StringUtil;

import java.util.List;

public class BwlAdapter extends RecyclerView.Adapter<BwlAdapter.ViewHolder>{

    private List<Bwl_event> mBwlList;

    public BwlAdapter(List<Bwl_event> bwlList){
        this.mBwlList = bwlList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bwl_event_item,parent,false);
        BwlAdapter.ViewHolder holder = new BwlAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bwl_event be = mBwlList.get(position);
        String content = StringUtil.getContent_bwl(be.getContent());
        String time = be.getTime();
        final String color = be.getColor();
        holder.tv_content.setText(content);
        holder.tv_time.setText("2019-4-25 13:27:18");
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApplication.getContext(), "点击了备忘录，颜色为："+color, Toast.LENGTH_SHORT).show();
            }
        });
        GradientDrawable p = (GradientDrawable) holder.layout.getBackground();
        p.setColor(Color.parseColor(color));
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
