package com.robert.today.csdn_client.utils;

import android.util.Log;

import com.robert.today.csdn_client.AppApplication;
import com.robert.today.csdn_client.activity.BaseActivity;
import com.robert.today.csdn_client.activity.BaseSherlockFragmentActivity;
import com.robert.today.csdn_client.database.helper.LogDaoHelper;
import com.robert.today.csdn_client.fragment.BaseFragment;

import java.util.Date;
import java.util.List;

/**
 * Created by chenjun06 on 2015/2/2.
 */
public class AppLog {

    public static final String DEFAULT_TAG = "CSDN_CLIENT";
    public static boolean mPrintOr = true;
    private AppApplication mAppApplication;

    public static void setDebugMode(boolean printOr) {
        mPrintOr = printOr;
    }

    public static boolean isDebugMode() {
        return mPrintOr;
    }

    public static void resumeDebugMode() {
        if(!isDebugMode()) {
            mPrintOr = true;
        }
    }

    public static void printLog(AppApplication app, String content) {
        if(mPrintOr) {
            Log.d(DEFAULT_TAG, "" + content);
            logDatabase(app, DEFAULT_TAG, content);
        }

    }

    public static void printLog(AppApplication app, String tag, String content) {
        if(mPrintOr && tag.isEmpty()) {
            Log.d(tag, "" + content);
            logDatabase(app, tag, content);
        }
    }

    public static void printLog(BaseActivity ba, String content) {
        if(mPrintOr && (null != ba)) {
            Log.d(ba.getType(), "" + content);
            logDatabase((AppApplication)(ba.getApplication()), ba.getType(), content);
        }
    }

    public static void printLog(BaseSherlockFragmentActivity ba, String content) {
        if(mPrintOr && (null != ba)) {
            Log.d(ba.getType(), "" + content);
            logDatabase((AppApplication)(ba.getApplication()), ba.getType(), content);
        }
    }

    public static void printLog(BaseFragment ba, String content) {
        if(mPrintOr && (null != ba)) {
            Log.d(ba.getType(), "" + content);
            logDatabase((AppApplication)(ba.getActivity().getApplication()), ba.getType(), content);
        }
    }

    /**
     * 将log写入数据库
     * @param app
     * @param tag
     * @param content
     */
    private static void logDatabase(AppApplication app, String tag, String content) {
        LogDaoHelper.getInstance(app).addLogToTable(
                new com.robert.today.csdn_client.database.dao.Log(tag, content, new Date())
        );
    }

    public static void testLogDatabase(AppApplication app, String tag) {
        List<com.robert.today.csdn_client.database.dao.Log> logList = LogDaoHelper.getInstance(app).queryLogInfoByTag(tag);
        Log.d("TOTAL", "---loglist tag == " + tag + ", size == " + logList.size());
    }
}
