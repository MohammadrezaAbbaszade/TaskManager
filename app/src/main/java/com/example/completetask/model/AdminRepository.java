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

public class AdminRepository {
    private static AdminRepository ourInstance;
    private DaoSession daoSession;
    private AdminDao adminDao;


    private AdminRepository() {
        daoSession = TaskDBApplication.getInstance().getDaoSession();
        adminDao = daoSession.getAdminDao();
    }

    public static AdminRepository getInstance() {
        if (ourInstance == null) {
            ourInstance = new AdminRepository();
        }
        return ourInstance;
    }

    public void addAdmin(Admin admin) {
        if (checkUserName(admin.getMUserName())) {
            throw new IllegalArgumentException("This UserName Is Exist!");
        }

        adminDao.insert(admin);
    }

    public boolean checkUserName(String username) {
        List<Admin> admins = getAdmins();
        for (Admin admin : admins) {
            if (admin.getMUserName().equals(username))
                return true;
        }
        return false;
    }

    public boolean login(String username, String password) {
        List<Admin> admins = getAdmins();
        for (Admin admin : admins) {
            if (admin.getMUserName().equals(username) &&
                    admin.getMPassword().equals(password))
                return true;
        }
        return false;
    }

    public List<Admin> getAdmins() {
        return adminDao.loadAll();
    }

    public Admin getAdmin(Long uuid) {
        return adminDao.queryBuilder()
                .where(AdminDao.Properties.MID.eq(uuid)).unique();
    }
}
