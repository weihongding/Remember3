package com.example.remember.listener;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.example.remember.R;

public class BtnLongListener implements View.OnLongClickListener {

    private Activity mActivity;

    public BtnLongListener(Activity activity) {
        mActivity = activity;
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rc_add: {
                View layout_rc_add = (View) mActivity.findViewById(R.id.layout_rc_add);
                View layout_rc_unq = (View) mActivity.findViewById(R.id.layout_rc_unq);
                layout_rc_add.setVisibility(View.GONE);
                layout_rc_unq.setVisibility(View.VISIBLE);
                break;
            }
        }

        return true;

    }
}
