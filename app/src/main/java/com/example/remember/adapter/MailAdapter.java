package com.example.remember.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.remember.R;
import com.example.remember.db.Mail;
import com.example.remember.util.ToastUtil;

import java.util.List;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.ViewHolder>{

    private List<Mail> mMailList;

    public MailAdapter(List<Mail> mailList){
        this.mMailList = mailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mail_item,parent,false);
        MailAdapter.ViewHolder holder = new MailAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Mail mail = mMailList.get(position);
        String type = mail.getType();
        String ac_se = mail.getSentUser();
        holder.tv_ac_se.setText(ac_se);
        holder.tv_type.setText(type);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ToastUtil("点击");
            }
        });
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new ToastUtil("长按");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMailList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView tv_type;
        TextView tv_ac_se;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = (LinearLayout)view.findViewById(R.id.layout_mail_item);
            tv_type = (TextView)view.findViewById(R.id.text_mail_item_type);
            tv_ac_se = (TextView)view.findViewById(R.id.text_mail_item_ac_se);
        }
    }



}
