package com.robert.today.csdn_client.activity;

import android.os.Bundle;

import com.robert.today.csdn_client.R;

/**
 * Created by chenjun06 on 2015/1/9.
 */
public class NewsDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setType("NewsDetailActivity");
        setContentView(R.layout.activity_loading);
    }
}
