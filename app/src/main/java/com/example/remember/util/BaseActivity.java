package com.example.remember.util;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private static Activity mCurrentActivity;

    @Override
    protected void onResume() {
        setCurrentActivity(this);
        super.onResume();
    }

    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    private void setCurrentActivity(Activity activity) {
        mCurrentActivity = activity;
    }

}
