package com.robert.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import net.simonvt.menudrawer.SlidingDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjun06 on 2014/12/26.
 */
public class CustomDrawer extends SlidingDrawer {

    private List<TouchListener> mTouchListenerList;

    public CustomDrawer(Context context) {
        super(context);

        initTouchListenerList();
    }

    public CustomDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTouchListenerList();
    }

    public CustomDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initTouchListenerList();
    }

    public void registerTouchListener(TouchListener touchListener) {
        mTouchListenerList.add(touchListener);
    }

    public void unregisterTouchListener(TouchListener touchListener) {
        mTouchListenerList.remove(touchListener);
    }

    private void initTouchListenerList() {
        if(null == mTouchListenerList) {
            mTouchListenerList = new ArrayList<TouchListener>();
        } else {
            mTouchListenerList.clear();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    public interface TouchListener {
        public TouchState getTouchState();
    }

    public enum TouchState {
        CHILD_FRAGMENT, FRAGMENT_ONE, FRAGMENT_OTHERS
    }
}
