package com.robert.today.csdn_client.database.helper;

import com.robert.today.csdn_client.AppApplication;
import com.robert.today.csdn_client.database.dao.DaoSession;
import com.robert.today.csdn_client.database.dao.Log;
import com.robert.today.csdn_client.database.dao.LogDao;

import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by chenjun06 on 2015/1/30.
 */
public class LogDaoHelper {

    private static LogDaoHelper mLogDaoHelper;
    private LogDao mLogDao;

    public static LogDaoHelper getInstance(AppApplication appApplication) {
        if(null == mLogDaoHelper) {
            mLogDaoHelper = new LogDaoHelper();
        }

        // 获取LogDao
        DaoSession daoSession = appApplication.getDaoSession();
        mLogDaoHelper.mLogDao = daoSession.getLogDao();
        return mLogDaoHelper;
    }

    /**
     * 添加数据
     * @param log
     */
    public void addLogToTable(Log log) {
        mLogDao.insert(log);
    }

    /**
     * 获取Log信息
     * @return
     */
    public List<Log> getLogInfoList() {
        QueryBuilder<Log> qb = mLogDao.queryBuilder();
        return qb.list();
    }

    /**
     * 获取全部Log信息
     * @return
     */
    public List<Log> getAllLogList() {
        return mLogDao.loadAll();
    }

    /**
     * 查询该条数据是否存在
     * @param id
     * @return
     */
    public boolean isSaved(int id) {
        QueryBuilder<Log> qb = mLogDao.queryBuilder();
        qb.where(LogDao.Properties.Id.eq(id));
        qb.buildCount().count();
        return qb.buildCount().count() > 0 ? true : false;
    }

    /**
     * 依照log id号删除log info
     * @param id
     */
    public void deleteLogInfo(int id) {
        QueryBuilder<Log> qb = mLogDao.queryBuilder();
        DeleteQuery<Log> dq = qb.where(LogDao.Properties.Id.eq(id)).buildDelete();
        dq.executeDeleteWithoutDetachingEntities();
    }

    /**
     * 清空log info
     */
    public void clearLogInfo() {
        mLogDao.deleteAll();
    }

    /**
     * 通过Tag列出所有的Log
     * @param tag
     * @return
     */
    public List<Log> queryLogInfoByTag(String tag) {
        QueryBuilder<Log> qb = mLogDao.queryBuilder();
        qb.where(LogDao.Properties.Tag.eq(tag));
        qb.orderAsc(LogDao.Properties.Date);
        return qb.build().list();
    }

    /**
     * 通过log内容查询log tag
     * @param content
     * @return
     */
    public String queryLogTag(String content) {
        QueryBuilder<Log> qb = mLogDao.queryBuilder();
        qb.where(LogDao.Properties.Tag.eq(content));
        List<Log> logList = qb.build().list();
        if(0 < logList.size()) {
            return logList.get(0).getTag();
        }
        return null;
    }

    /**
     * 列出在XXX时间之后打印的所有log
     * @param date
     * @return
     */
    public List<Log> queryUpLogListByDate(Date date) {
        QueryBuilder<Log> qb = mLogDao.queryBuilder();
        qb.where(LogDao.Properties.Date.gt(date));
        qb.orderAsc(LogDao.Properties.Date);
        return qb.build().list();
    }

    /**
     * 列出在XXX时间之前打印的所有log
     * @param date
     * @return
     */
    public List<Log> queryDownLogListByDate(Date date) {
        QueryBuilder<Log> qb = mLogDao.queryBuilder();
        qb.where(LogDao.Properties.Date.lt(date));
        qb.orderAsc(LogDao.Properties.Date);
        return qb.build().list();
    }
}
