package com.robert.today.csdn_client.model;

import android.content.Context;

import com.robert.library.volley.RequestQueue;
import com.robert.library.volley.toolbox.Volley;

import java.io.Serializable;

/**
 * Created by chenjun06 on 2015/1/19.
 */
public class GlobalStagedData implements Serializable {

    private static GlobalStagedData mGlobalStagedData;

    private RequestQueue mVolleyRequestQueue;

    public static GlobalStagedData getInstance() {
        if(null == mGlobalStagedData) {
            mGlobalStagedData = new GlobalStagedData();
        }
        return mGlobalStagedData;
    }

    public void initGlobalData(Context context) {
        initVolley(context);
    }

    public RequestQueue getVolleyRequestQueue() {
        return mVolleyRequestQueue;
    }

    private void initVolley(Context ctx) {
        mVolleyRequestQueue = Volley.newRequestQueue(ctx);
    }
}
