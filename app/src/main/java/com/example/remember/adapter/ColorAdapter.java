package com.example.remember.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remember.R;
import com.example.remember.activity.BwlDetailActivity;
import com.example.remember.activity.JlDetailActivity;
import com.example.remember.activity.RcActivity;
import com.example.remember.db.Rc_q;
import com.example.remember.util.BaseActivity;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.MyApplication;
import com.example.remember.util.MyDialog;
import com.example.remember.util.ViewUtil;

import java.util.Arrays;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {

    private List<String> mColorList = Arrays.asList(ColorUtil.colors);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_color_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String color = mColorList.get(position);
        GradientDrawable p = (GradientDrawable) holder.btn_choose_color.getBackground();
        p.setColor(Color.parseColor(color));

        holder.btn_choose_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseActivity.getCurrentActivity() instanceof BwlDetailActivity){
                    ColorUtil.choose_color_bwl = color;
                    Button btn_color = BaseActivity.getCurrentActivity().findViewById(R.id.btn_bwl_color);
                    ViewUtil.setViewColor(btn_color,color);
                    MyDialog.colorDialog_bwl.hide();
                }else if (BaseActivity.getCurrentActivity() instanceof JlDetailActivity){
                    ColorUtil.choose_color_jlDetail_set = color;
                    Button btn_color = BaseActivity.getCurrentActivity().findViewById(R.id.btn_jl_detail_color);
                    ViewUtil.setViewColor(btn_color,color);
                    MyDialog.colorDialog_jl.hide();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mColorList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        Button btn_choose_color;

        public ViewHolder(@NonNull View view) {
            super(view);
            btn_choose_color = (Button)view.findViewById(R.id.btn_choose_color);
        }
    }

}
