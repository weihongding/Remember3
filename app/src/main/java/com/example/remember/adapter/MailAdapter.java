package com.example.remember.adapter;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.remember.R;
import com.example.remember.db.Bwl_event;
import com.example.remember.db.Jl;
import com.example.remember.db.Jl_sj_event;
import com.example.remember.db.Jl_zb_event;
import com.example.remember.db.Mail;
import com.example.remember.util.DataUtil;
import com.example.remember.util.DateUtil;
import com.example.remember.util.DbUtil;
import com.example.remember.util.LogdUtil;
import com.example.remember.util.ObjectUtil;
import com.example.remember.util.StringUtil;
import com.example.remember.util.ToastUtil;
import com.example.remember.util.UserSetting;

import java.util.Date;
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
        Log.d(DataUtil.TAG, mail.getObjStr());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                String type = mail.getType();

                if (type.equals(StringUtil.eventType_bwl)){
                    Bwl_event be = ObjectUtil.mailToBe(mail);
                    be.save();
                }else if (type.equals(StringUtil.eventType_sj)){

                    JSONObject json = JSONObject.parseObject(mail.getObjStr());
                    String jlStr = json.getString("Jl");
                    String listStr = json.getString("JlDetail");
                    JSONObject jlJson = JSONObject.parseObject(jlStr);
                    Jl jl = new Jl(jlJson.getString("type"),jlJson.getString("title"),jlJson.getString("des"),
                            jlJson.getString("color"), DateUtil.dateToStr(new Date()));
                    jl.save();
                    int jlId = DbUtil.requestLastJlId();
                    JSONArray ja = JSON.parseArray(listStr);
                    List<Jl_sj_event> list = JSONObject.parseArray(ja.toJSONString(), Jl_sj_event.class);
                    for (Jl_sj_event jse:list) {
                        jse.setJid(jlId);
                        jse.save();
                        new LogdUtil("jse="+jse.getJid()+","+jse.getTime());
                    }

                }else if (type.equals(StringUtil.eventType_zb)){

                    JSONObject json = JSONObject.parseObject(mail.getObjStr());
                    String jlStr = json.getString("Jl");
                    String listStr = json.getString("JlDetail");
                    JSONObject jlJson = JSONObject.parseObject(jlStr);
                    Jl jl = new Jl(jlJson.getString("type"),jlJson.getString("title"),jlJson.getString("des"),
                            jlJson.getString("color"), DateUtil.dateToStr(new Date()));
                    jl.save();
                    int jlId = DbUtil.requestLastJlId();
                    JSONArray ja = JSON.parseArray(listStr);
                    List<Jl_zb_event> list = JSONObject.parseArray(ja.toJSONString(), Jl_zb_event.class);
                    for (Jl_zb_event jze:list) {
                        jze.setJid(jlId);
                        jze.save();
                        new LogdUtil("jse="+jze.getJid()+","+jze.getTime());
                    }

                }else if (type.equals(StringUtil.eventType_sb)){
                    String key = mail.getObjStr();
                    UserSetting.putSbKey(key);
                }

                new ToastUtil(type+"添加成功");
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
