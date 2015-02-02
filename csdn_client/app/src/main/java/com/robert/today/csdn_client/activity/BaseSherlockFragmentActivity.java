package com.robert.today.csdn_client.activity;

import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * Created by chenjun06 on 2015/2/2.
 */
public class BaseSherlockFragmentActivity extends SherlockFragmentActivity {
    private String mType = "BaseSherlockFragmentActivity";

    public void setType(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }
}
