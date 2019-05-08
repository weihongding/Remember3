package com.example.remember.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.remember.R;
import com.example.remember.entity.UserInfo;
import com.example.remember.util.BaseActivity;
import com.example.remember.util.CheckUtil;
import com.example.remember.util.HttpUtil;
import com.example.remember.util.LogdUtil;
import com.example.remember.util.MyApplication;
import com.example.remember.util.ObjectUtil;
import com.example.remember.util.StringUtil;
import com.example.remember.util.ToastUtil;
import com.example.remember.util.UserSetting;
import com.example.remember.util.ViewUtil;
import com.example.remember.util.MyDialog;
import com.example.remember.listener.MaBtnListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_share = (Button)findViewById(R.id.btn_mail);
        Button btn_set = (Button)findViewById(R.id.btn_set);
        Button btn_rc = (Button)findViewById(R.id.btn_rc);
        Button btn_bwl = (Button)findViewById(R.id.btn_bwl);
        Button btn_jl = (Button)findViewById(R.id.btn_jl);
        Button btn_tq = (Button)findViewById(R.id.btn_tq);
        Button btn_dt = (Button)findViewById(R.id.btn_dt);
        Button btn_sb = (Button)findViewById(R.id.btn_sb);
        Button btn_login = (Button)findViewById(R.id.btn_login);
        View logView = this.getLayoutInflater().inflate(R.layout.login, null);
        View regView = this.getLayoutInflater().inflate(R.layout.register, null);

        btn_share.setTypeface(ViewUtil.font);
        btn_set.setTypeface(ViewUtil.font);
        MaBtnListener listener = new MaBtnListener(this);

        btn_share.setOnClickListener(listener);
        btn_set.setOnClickListener(listener);
        btn_rc.setOnClickListener(listener);
        btn_bwl.setOnClickListener(listener);
        btn_jl.setOnClickListener(listener);
        btn_tq.setOnClickListener(listener);
        btn_dt.setOnClickListener(listener);
        btn_sb.setOnClickListener(listener);
        btn_login.setOnClickListener(listener);
        MyDialog.loginDialog=new MyDialog(this, logView, R.style.DialogTheme);
        MyDialog.regDialog=new MyDialog(this, regView, R.style.DialogTheme);
        MyDialog.loginDialog.setCancelable(true);
        MyDialog.regDialog.setCancelable(true);

        //验证登陆
        CheckUtil.setUserLoginState(this);
        int id = UserSetting.getIifId();
        if (id!=0){
            Intent intent = new Intent(this,ObjectUtil.getIifClass(id));
            startActivity(intent);
        }

    }

    @Override
    protected void onResume() {
        if (UserSetting.getLogined() == 0){
            CheckUtil.setUserLoginState(this);
            UserSetting.setLogined(1);
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        MyDialog.loginDialog.dismiss();
        MyDialog.regDialog.dismiss();
        if (!UserSetting.getAutoSave()){
            UserSetting.setUserLoginInfo(this,"","");
            UserSetting.setUserInfo(new UserInfo());
        }
        UserSetting.setIsStart();
        super.onDestroy();
    }

}
