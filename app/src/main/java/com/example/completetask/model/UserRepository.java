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

public class UserRepository {
    private static UserRepository ourInstance;
    private SQLiteDatabase mDatabase;
    private Context mContext;


    private UserRepository(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TaskOpenHelper(mContext).getWritableDatabase();
    }

    public static UserRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new UserRepository(context);
        }
        return ourInstance;
    }
    public void addUser(User user) {
        if (checkUserName(user.getmUserName())) {
            throw new IllegalArgumentException("This UserName Is Exist!");
        }

        ContentValues values = getContentValues(user);
        mDatabase.insertOrThrow(TaskDBSchema.User.NAME, null, values);
    }
    public boolean checkUserName(String username) {
        List<User> users =getmUsers();
        for (User user : users) {
            if (user.getmUserName().equals(username))
                return true;
        }
        return false;
    }
    public boolean login(String username, String password) {
        List<User> users =getmUsers();
        for (User user : users) {
            if (user.getmUserName().equals(username) &&
                    user.getmPassword().equals(password))
                return true;
        }
        return false;
    }
    public List<User> getmUsers() {
        List<User> users = new ArrayList<>();
       UserCursorWrapper cursor = queryUser(null, null);
        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                users.add(cursor.getUser());

                cursor.moveToNext();
            }

        } finally {
            cursor.close();
        }
        return users;

    }
    public User getUser(UUID uuid) {
        String[] whereArgs = new String[]{uuid.toString()};
       UserCursorWrapper cursor = queryUser(TaskDBSchema.User.Cols.UUID + " = ?", whereArgs);

        try {
            if (cursor == null || cursor.getCount() == 0)
                return null;

            cursor.moveToFirst();
            return cursor.getUser();

        } finally {
            cursor.close();
        }
    }
    private UserCursorWrapper queryUser(String where, String[] whereArgs) {
        Cursor cursor= mDatabase.query(TaskDBSchema.User.NAME,
                null,
                where,
                whereArgs,
                null,
                null,
                null);
        return new UserCursorWrapper(cursor);
    }
    private ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(TaskDBSchema.User.Cols.UUID, user.getUUID().toString());
        values.put(TaskDBSchema.User.Cols.PASSWORD, user.getmPassword());
        values.put(TaskDBSchema.User.Cols.USERNAME, user.getmUserName());

        return values;
    }
}
