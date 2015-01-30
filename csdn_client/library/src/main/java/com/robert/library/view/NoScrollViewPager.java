package com.robert.library.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by chenjun06 on 2015/1/29.
 * viewpager禁止左右滑动，当然引发的是指示器也跟着不动了
 *
 * onInterceptTouchEvent 返回false,则后续再来的事件(比如ACTION_UP)会继续传递给子view的ontouchEvent ,
 * onInterceptTouchEvent 返回true,则后续再来的事件(比如ACTION_UP)就不会传递给子view.
 * view的onTouchEvent返回true,则表示事件已经消化干净,viewgroup的onTouchEvent将不会被调用,否则相反.
 *
 * android里一般返回true的都表示事件被消费了，不往其他地方传递事件了。
 */
public class NoScrollViewPager extends ViewPager {

    private boolean mCanScroll = true;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean canScroll) {
        mCanScroll = canScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(!mCanScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(!mCanScroll) {
            return false;
        }
        return super.onTouchEvent(ev);
    }
}
