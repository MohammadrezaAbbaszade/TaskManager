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

public class DoingListsRepository {
    private static DoingListsRepository ourInstance;
    private DaoSession daoSession;
    private DoingDao doingDao;


    private DoingListsRepository() {
        daoSession = TaskDBApplication.getInstance().getDaoSession();
        doingDao = daoSession.getDoingDao();
    }

    public static DoingListsRepository getInstance() {
        if (ourInstance == null) {
            ourInstance = new DoingListsRepository();
        }
        return ourInstance;
    }

    public List<Doing> getDoings(String username) {
        List<Doing> doingList;
        doingList = doingDao.loadAll();
        List<Doing> mDoingList = new ArrayList<>();
        for (Doing doing : doingList)
            if (doing.getMUserName().equals(username))
                mDoingList.add(doing);


        return mDoingList;
    }

    public Doing getDoing(Long uuid) {
       return doingDao.queryBuilder()
               .where(DoingDao.Properties.MID.eq(uuid)).unique();
    }

    public void insertDoing(Doing doing) {
       doingDao.insert(doing);
    }

    public void updateDoing(Doing doing) throws Exception {
       doingDao.update(doing);
    }

    public void deleteDoing(Doing doing) throws Exception {
        Doing d = getDoing(doing.getMID());
        if (d == null)
            throw new Exception("This task does not exist!!!");
        doingDao.delete(d);
    }

    public void deleteDoings(List<Doing> doings) throws Exception {
        for (Doing d : doings) {
            Doing d2 = getDoing(d.getMID());
            if (d2 == null)
                throw new Exception("You Dont Have Any Tasks  To Delete!!!");
            doingDao.deleteByKey(d.getMID());
        }
    }

}
