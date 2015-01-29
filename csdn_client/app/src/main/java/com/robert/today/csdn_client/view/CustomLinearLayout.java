package com.robert.today.csdn_client.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by chenjun06 on 2015/1/28.
 * 简易的Bounce效果
 * 1. setPadding()的使用：可以只显示布局的一部分
 * 2. MotionEvent事件的处理
 * 3. scrollTo()和scrollBy()的运用
 * 4. postDelayed()的运用
 */
public class CustomLinearLayout extends LinearLayout {

    private int mHeaderHeight = 0, mFooterHeight = 0;
    private boolean mIsPull = false;
    private int mTotalChangeY = 0;
    private float mDownY = 0, mOldDownY = 0;

    public CustomLinearLayout(Context context) {
        super(context);
        mIsPull = false;
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mIsPull = false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mHeaderHeight = getChildAt(0).getMeasuredHeight();
        mFooterHeight = getChildAt(2).getMeasuredHeight();

        resetInitState();
    }

    private void resetInitState() {
        setPadding(0, -mHeaderHeight, 0, mFooterHeight);
    }

    private boolean canUpMove() {
        return getChildAt(1).getTop() >= getTop()?true:false;
    }

    private boolean canDownMove() {
        return getChildAt(1).getBottom() <= getBottom()?true:false;
    }

    private boolean upOrDown(int changeY) {
        return changeY > 0 ? true : false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(!mIsPull) {
            return true;
        }
//        return true;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TAG", "---onTouchEvent event == " + event);
        int changeY = 0;
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mIsPull = true;
                mDownY = event.getRawY();
                mOldDownY = mDownY;
                mTotalChangeY = 0;
                return true;
            case MotionEvent.ACTION_MOVE:
                mDownY = event.getRawY();
                changeY = (int)(mDownY - mOldDownY);
                mTotalChangeY += changeY;
                Log.d("TAG", "---111111onTouchEvent changeY == " + upOrDown(changeY));
                if(upOrDown(changeY)) { // up
                    Log.d("TAG", "---111111onTouchEvent canMoveUp == " + canUpMove());
                    if(canUpMove() && (mIsPull)) {    // 不能超过header高度
                        if (mHeaderHeight < changeY) {
                            changeY = mHeaderHeight;
                        }
                        Log.d("TAG", "---111111onTouchEvent scroll == " + changeY);
                        scrollChange(-changeY);
//                        scrollBy(0, changeY);
                    }
                } else {
                    if(canDownMove()) {    // 不能超过header高度
                        if (mFooterHeight < changeY) {
                            changeY = mFooterHeight;
                        }
                        scrollChange(-changeY);
//                        scrollBy(0, changeY);
                    }
                }
                mOldDownY = mDownY;
                return true;
            case MotionEvent.ACTION_UP:
                resetInitState();   // 没起啥作用
                Log.d("TAG", "-----222222total changY == " + mTotalChangeY);
                scrollChange(0); // 回到初始化状态
//                scrollBy(0, -mTotalChangeY);
                mTotalChangeY = 0;
                mIsPull = false;
                return true;
        }
//        return true;
        return super.onTouchEvent(event);
    }

    private void scrollChange(final int changeY) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollTo(0, changeY);
            }
        }, 100);
    }
}
