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
import com.example.remember.entity.Sb;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.MyApplication;

import java.util.List;

public class SbAdapter extends RecyclerView.Adapter<SbAdapter.ViewHolder> {

    private List<Sb> mSbList;

    public SbAdapter(List<Sb> sbList){
        this.mSbList = sbList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sb_item,parent,false);
        SbAdapter.ViewHolder holder = new SbAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SbAdapter.ViewHolder holder, int position) {
        Sb sb = mSbList.get(position);
        String name = sb.getName();
        String state = sb.getState();
        String time = sb.getTime();
        String color = ColorUtil.getStateColor(state);

        holder.tv_name.setText(name);
        holder.tv_state.setText(state);
        holder.tv_time.setText(time);
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MyApplication.getContext(), "长按了Item", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        GradientDrawable p = (GradientDrawable) holder.layout.getBackground();
        p.setColor(Color.parseColor(color));

    }

    @Override
    public int getItemCount() {
        return mSbList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layout;
        TextView tv_name;
        TextView tv_state;
        TextView tv_time;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = (LinearLayout)view.findViewById(R.id.layout_sb_item);
            tv_name = (TextView)view.findViewById(R.id.text_sb_item_name);
            tv_state = (TextView)view.findViewById(R.id.text_sb_item_state);
            tv_time = (TextView)view.findViewById(R.id.text_sb_item_time);
        }
    }

}
