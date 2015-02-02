package com.robert.today.csdn_client.activity;

import android.app.Activity;

/**
 * Created by chenjun06 on 2015/2/2.
 */
public class BaseActivity extends Activity {

    private String mType = "BaseActivity";

    public void setType(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }
}
