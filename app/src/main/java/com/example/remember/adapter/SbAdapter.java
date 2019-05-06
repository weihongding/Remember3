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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.entity.Sb;
import com.example.remember.listener.BtnListener;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.MyApplication;
import com.example.remember.util.MyDialog;
import com.example.remember.util.UserSetting;
import com.example.remember.util.ViewUtil;

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
        final Sb sb = mSbList.get(position);
        final String name = sb.getName();
        final String key = sb.getKey();
        String state = sb.getState();
        String time = sb.getTime();
        String color = ColorUtil.getStateColor(state);

        holder.tv_name.setText(name);
        holder.tv_state.setText(state);
        holder.tv_time.setText(time);
        ViewUtil.setViewColor(holder.layout,color);

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                EditText et_remark = (EditText)MyDialog.sbDialog_long.findViewById(R.id.edit_sb_long_remark);
                TextView tv_key = (TextView)MyDialog.sbDialog_long.findViewById(R.id.text_sb_long_key);
                Button btn_del = (Button)MyDialog.sbDialog_long.findViewById(R.id.btn_sb_long_del);
                Button btn_save = (Button)MyDialog.sbDialog_long.findViewById(R.id.btn_sb_long_save);

                et_remark.setText(UserSetting.getKeyRemark(key));
                tv_key.setText(key);
                btn_del.setOnClickListener(BtnListener.instance);
                btn_save.setOnClickListener(BtnListener.instance);

                MyDialog.sbDialog_long.show();

                return true;
            }
        });

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
