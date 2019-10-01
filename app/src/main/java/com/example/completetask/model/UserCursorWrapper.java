package com.example.completetask.model;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.completetask.database.TaskDBSchema;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public User getUser() {
        String strUUID = getString(getColumnIndex(TaskDBSchema.User.Cols.UUID));
        String password = getString(getColumnIndex(TaskDBSchema.User.Cols.PASSWORD));
        String usernamee = getString(getColumnIndex(TaskDBSchema.User.Cols.USERNAME));
        String timeRegister = getString(getColumnIndex(TaskDBSchema.User.Cols.TIMETOREGISTER));

        User user = new User(UUID.fromString(strUUID));
        user.setmPassword(password);
        user.setmUserName(usernamee);
        user.setTimeRegister(timeRegister);
        return user;
    }
}
