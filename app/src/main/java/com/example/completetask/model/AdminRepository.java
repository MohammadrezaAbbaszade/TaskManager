package com.example.completetask.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.completetask.database.TaskDBSchema;
import com.example.completetask.database.TaskOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminRepository {
    private static AdminRepository ourInstance;
    private SQLiteDatabase mDatabase;
    private Context mContext;


    private AdminRepository(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TaskOpenHelper(mContext).getWritableDatabase();
    }

    public static AdminRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AdminRepository(context);
        }
        return ourInstance;
    }
    public void addAdmin(Admin admin) {
        if (checkUserName(admin.getmUserName())) {
            throw new IllegalArgumentException("This UserName Is Exist!");
        }

        ContentValues values = getContentValues(admin);
        mDatabase.insertOrThrow(TaskDBSchema.Admin.NAME, null, values);
    }
    public boolean checkUserName(String username) {
        List<Admin> admins =getAdmins();
        for (Admin admin : admins) {
            if (admin.getmUserName().equals(username))
                return true;
        }
        return false;
    }
    public boolean login(String username, String password) {
        List<Admin> admins =getAdmins();
        for (Admin admin : admins) {
            if (admin.getmUserName().equals(username) &&
                    admin.getmPassword().equals(password))
                return true;
        }
        return false;
    }
    public List<Admin> getAdmins() {
        List<Admin> admins = new ArrayList<>();
        AdminCursorWrapper cursor = queryAdmin(null, null);
        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

              admins.add(cursor.getAdmin());

                cursor.moveToNext();
            }

        } finally {
            cursor.close();
        }
        return admins;

    }
    public Admin getAdmin(UUID uuid) {
        String[] whereArgs = new String[]{uuid.toString()};
        AdminCursorWrapper cursor = queryAdmin(TaskDBSchema.Admin.Cols.UUID + " = ?", whereArgs);

        try {
            if (cursor == null || cursor.getCount() == 0)
                return null;

            cursor.moveToFirst();
            return cursor.getAdmin();

        } finally {
            cursor.close();
        }
    }
    private AdminCursorWrapper queryAdmin(String where, String[] whereArgs) {
        Cursor cursor= mDatabase.query(TaskDBSchema.Admin.NAME,
                null,
                where,
                whereArgs,
                null,
                null,
                null);
        return new AdminCursorWrapper(cursor);
    }
    private ContentValues getContentValues(Admin admin) {
        ContentValues values = new ContentValues();
        values.put(TaskDBSchema.Admin.Cols.UUID, admin.getUUID().toString());
        values.put(TaskDBSchema.Admin.Cols.PASSWORD, admin.getmPassword());
        values.put(TaskDBSchema.Admin.Cols.USERNAME, admin.getmUserName());

        return values;
    }
}
