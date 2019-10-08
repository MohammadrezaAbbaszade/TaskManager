package com.example.completetask.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.completetask.database.TaskDBApplication;
import com.example.completetask.database.TaskOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoneListsRepository {
    private static DoneListsRepository ourInstance;
    private DaoSession daoSession;
    private DoneDao doneDao;


    private DoneListsRepository() {
        daoSession = TaskDBApplication.getInstance().getDaoSession();
        doneDao = daoSession.getDoneDao();
    }

    public static DoneListsRepository getInstance() {
        if (ourInstance == null) {
            ourInstance = new DoneListsRepository();
        }
        return ourInstance;
    }

    public List<Done> getDones(String username) {
        List<Done> doneList;
        doneList = doneDao.loadAll();
        List<Done> mDoneList = new ArrayList<>();
        for (Done done : doneList)
            if (done.getMUserName().equals(username))
                mDoneList.add(done);


        return mDoneList;

    }

    public Done getDone(Long uuid) {
        return doneDao.queryBuilder().where(DoneDao.Properties.MID.eq(uuid))
                .unique();
    }

    public void insertDone(Done done) {
        doneDao.insert(done);
    }

    public void updateDone(Done done) throws Exception {
        doneDao.update(done);
    }

    public void deleteDone(Done done) throws Exception {
        Done d = getDone(done.getMID());
        if (d == null)
            throw new Exception("This task does not exist!!!");
        doneDao.delete(d);
    }

    public void deleteDones(List<Done> dones) throws Exception {
        for (Done d : dones) {
            Done d2 = getDone(d.getMID());
            if (d2 == null)
                throw new Exception("You Dont Have Any Tasks  To Delete!!!");
            doneDao.deleteByKey(d.getMID());
        }
    }
}
