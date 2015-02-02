package com.robert.mymodule.app2;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by chenjun06 on 2015/1/30.
 */
public class LogDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.robert.today.csdn_client.database.dao");
        addLog(schema);

        new DaoGenerator().generateAll(schema, "./");
    }

    public static void addLog(Schema schema) {
        Entity log = schema.addEntity("Log");
        log.addIdProperty();
        log.addStringProperty("tag").notNull();
        log.addStringProperty("content").notNull();
        log.addDateProperty("date");
    }
}
