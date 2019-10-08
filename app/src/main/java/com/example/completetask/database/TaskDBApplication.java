package com.example.completetask.database;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.completetask.model.DaoMaster;
import com.example.completetask.model.DaoSession;

public class TaskDBApplication extends Application {


    private static TaskDBApplication application;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        TaskOpenHelper daoOpenHelper = new TaskOpenHelper(this, "completeTask.db");
        SQLiteDatabase database = daoOpenHelper.getWritableDatabase();
        daoSession = new DaoMaster(database).newSession();

        application = this;
    }

    public static  TaskDBApplication getInstance() {
        return application;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
