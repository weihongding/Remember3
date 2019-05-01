package com.example.remember.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.db.Jl_sj_event;
import com.example.remember.util.MyApplication;

import java.util.List;

public class JlAdapter_sj extends RecyclerView.Adapter<JlAdapter_sj.ViewHolder> {

    private List<Jl_sj_event> mSjList;

    public JlAdapter_sj(List<Jl_sj_event> sjList){
        this.mSjList = sjList;
    }

    @NonNull
    @Override
    public JlAdapter_sj.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jl_detail_sj_item,parent,false);
        JlAdapter_sj.ViewHolder holder = new JlAdapter_sj.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Jl_sj_event jse = mSjList.get(position);
        holder.tv_time.setText(jse.getTime());
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MyApplication.getContext(), "长按了事件item", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSjList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView tv_time;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = (LinearLayout)view.findViewById(R.id.layout_jl_detail_sj_item);
            tv_time = (TextView)view.findViewById(R.id.text_jl_detail_sj_item);
        }
    }

}
