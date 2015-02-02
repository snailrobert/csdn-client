package com.robert.today.csdn_client;

import android.app.Application;
import android.content.Context;

import com.robert.today.csdn_client.database.dao.DaoMaster;
import com.robert.today.csdn_client.database.dao.DaoSession;
import com.robert.today.csdn_client.model.GlobalStagedData;

/**
 * Created by chenjun06 on 2015/1/19.
 */
public class AppApplication extends Application{

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalStagedData.getInstance().initGlobalData(getApplicationContext());
    }

    public DaoMaster getDaoMaster() {
        if(null == mDaoMaster) {
            DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(this.getApplicationContext(), Config.DATABASE_NAME, null);
            mDaoMaster = new DaoMaster(openHelper.getWritableDatabase());
        }
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        if(null == mDaoSession) {
            if(null == mDaoMaster) {
                getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

}
