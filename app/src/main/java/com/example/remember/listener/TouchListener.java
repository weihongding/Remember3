package com.example.remember.listener;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.remember.util.MyApplication;
import com.example.remember.util.ToastUtil;

public class TouchListener implements View.OnTouchListener {

    private float mPosX;
    private float mPosY;
    private float mCurPosX;
    private float mCurPosY;


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPosX = event.getX();
                mPosY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurPosX = event.getX();
                mCurPosY = event.getY();

                break;
            case MotionEvent.ACTION_UP:
                if (mCurPosX - mPosX > 0
                        && (Math.abs(mCurPosX - mPosX) > 25)) {
                    new ToastUtil("向左滑动");

                } else if (mCurPosX - mPosX < 0
                        && (Math.abs(mCurPosX - mPosX) > 25)) {
                    new ToastUtil("向右滑动");
                }
                break;
        }
        return true;
    }
}
