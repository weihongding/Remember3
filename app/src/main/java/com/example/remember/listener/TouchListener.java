package com.example.remember.listener;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.remember.util.MyApplication;

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
//                        if (mCurPosY - mPosY > 0
//                                && (Math.abs(mCurPosY - mPosY) > 25)) {
//                            //向下滑動
//                            tiShi(mContext,"向下");
//
//                        } else if (mCurPosY - mPosY < 0
//                                && (Math.abs(mCurPosY - mPosY) > 25)) {
//                            //向上滑动
//                            tiShi(mContext,"向上");
//                        }
                if (mCurPosX - mPosX > 0
                        && (Math.abs(mCurPosX - mPosX) > 25)) {
                    Toast.makeText(MyApplication.getContext(), "向左滑动", Toast.LENGTH_SHORT).show();

                } else if (mCurPosX - mPosX < 0
                        && (Math.abs(mCurPosX - mPosX) > 25)) {
                    Toast.makeText(MyApplication.getContext(), "向右滑动", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }
}
