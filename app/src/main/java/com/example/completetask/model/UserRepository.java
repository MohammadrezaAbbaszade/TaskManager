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

public class UserRepository {
    private static UserRepository ourInstance;
    private DaoSession daoSession;
    private UserDao userDao;


    private UserRepository() {
        daoSession = TaskDBApplication.getInstance().getDaoSession();
        userDao = daoSession.getUserDao();
    }

    public static UserRepository getInstance() {
        if (ourInstance == null) {
            ourInstance = new UserRepository();
        }
        return ourInstance;
    }
    public void addUser(User user) {
        if (checkUserName(user.getMUserName())) {
            throw new IllegalArgumentException("This UserName Is Exist!");
        }

       userDao.insert(user);
    }
    public boolean checkUserName(String username) {
        List<User> users =getmUsers();
        for (User user : users) {
            if (user.getMUserName().equals(username))
                return true;
        }
        return false;
    }
    public boolean login(String username, String password) {
        List<User> users =getmUsers();
        for (User user : users) {
            if (user.getMUserName().equals(username) &&
                    user.getMPassword().equals(password))
                return true;
        }
        return false;
    }
    public List<User> getmUsers() {
      return userDao.loadAll();
    }
    public User getUser(Long uuid) {
      return userDao.queryBuilder()
              .where(UserDao.Properties.MID.eq(uuid)).unique();
    }
    public void deleteUser(User user) throws Exception {
        User u = getUser(user.getMID());
        if (u == null)
            throw new Exception("This crime does not exist!!!");
        userDao.delete(u);
    }
}
