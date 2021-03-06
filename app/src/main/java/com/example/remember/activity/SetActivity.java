package com.example.remember.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.remember.R;
import com.example.remember.listener.BtnListener;
import com.example.remember.listener.MaBtnListener;
import com.example.remember.util.BaseActivity;
import com.example.remember.util.LogdUtil;
import com.example.remember.util.ObjectUtil;
import com.example.remember.util.StringUtil;
import com.example.remember.util.ToastUtil;
import com.example.remember.util.UserSetting;

public class SetActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        Button btn_unLogin = (Button)findViewById(R.id.btn_set_unlogin);
        Spinner spin_ini = (Spinner)findViewById(R.id.spin_set_ini);
        Spinner spin_bwl_textsize = (Spinner)findViewById(R.id.spin_set_bwltextsize);

        ArrayAdapter<String> mAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, StringUtil.iniInF);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_ini.setAdapter(mAdapter);
        spin_ini.setSelection(UserSetting.getIifId());

        ArrayAdapter<String> mAdapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, StringUtil.bwlTextSize);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_bwl_textsize.setAdapter(mAdapter1);
        spin_bwl_textsize.setSelection(ObjectUtil.sizeToOrder(UserSetting.getBwlTextSize()));

        btn_unLogin.setOnClickListener(BtnListener.instance);
        spin_ini.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserSetting.setIifId(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spin_bwl_textsize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserSetting.setBwlTextSize(ObjectUtil.orderToSize(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


}
