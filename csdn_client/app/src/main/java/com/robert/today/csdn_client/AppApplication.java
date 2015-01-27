package com.robert.today.csdn_client;

import android.app.Application;
import com.robert.today.csdn_client.model.GlobalStagedData;

/**
 * Created by chenjun06 on 2015/1/19.
 */
public class AppApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalStagedData.getInstance().initGlobalData(getApplicationContext());
    }

}
