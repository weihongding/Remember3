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
import com.example.remember.db.Rc_q;
import com.example.remember.util.ColorUtil;
import com.example.remember.util.MyApplication;
import com.example.remember.util.MyDialog;

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
                if (MyDialog.rcqDialog_set.isShowing()){
                    Button btn_color_set = (Button) MyDialog.rcqDialog_set.findViewById(R.id.btn_rc_q_set_color);
                    GradientDrawable p1 = (GradientDrawable) btn_color_set.getBackground();
                    p1.setColor(Color.parseColor(color));
                    if (Rc_q.chooseRcq!=null){
                        Rc_q.chooseRcq.setColor(color);
                    }
                }else if(MyDialog.rcqDialog_add.isShowing()){
                    Button btn_color_add = (Button) MyDialog.rcqDialog_add.findViewById(R.id.btn_rc_q_set_color);
                    GradientDrawable p2 = (GradientDrawable) btn_color_add.getBackground();
                    p2.setColor(Color.parseColor(color));
                }else if(MyDialog.rcunqDialog_set.isShowing()){
                    Button btn_color_unqset = (Button)MyDialog.rcunqDialog_set.findViewById(R.id.btn_rc_unq_set_color);
                    GradientDrawable p2 = (GradientDrawable) btn_color_unqset.getBackground();
                    p2.setColor(Color.parseColor(color));
                }
                MyDialog.colorDialog_rc.hide();
                Toast.makeText(MyApplication.getContext(), "点击颜色："+color, Toast.LENGTH_SHORT).show();
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
