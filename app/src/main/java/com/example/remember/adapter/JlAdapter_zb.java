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
import com.example.remember.db.Jl_zb_event;
import com.example.remember.util.MyApplication;

import java.util.List;

public class JlAdapter_zb extends RecyclerView.Adapter<JlAdapter_zb.ViewHolder> {

    private List<Jl_zb_event> mZbList;

    public JlAdapter_zb(List<Jl_zb_event> zbList){
        this.mZbList = zbList;
    }

    @NonNull
    @Override
    public JlAdapter_zb.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jl_detail_zb_item,parent,false);
        JlAdapter_zb.ViewHolder holder = new JlAdapter_zb.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Jl_zb_event jze = mZbList.get(position);
        holder.tv_time.setText(jze.getTime());
        holder.tv_content.setText(jze.getContent());
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
        return mZbList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView tv_time;
        TextView tv_content;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = (LinearLayout)view.findViewById(R.id.layout_jl_detail_zb_item);
            tv_time = (TextView)view.findViewById(R.id.text_jl_detail_zb_item_time);
            tv_content = (TextView)view.findViewById(R.id.text_jl_detail_zb_item_content);
        }
    }

}
