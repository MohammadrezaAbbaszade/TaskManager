package com.example.completetask.model;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.completetask.database.TaskDBSchema;

import java.util.UUID;

public class AdminCursorWrapper extends CursorWrapper {
    public AdminCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Admin getAdmin() {
        String strUUID = getString(getColumnIndex(TaskDBSchema.Admin.Cols.UUID));
        String password = getString(getColumnIndex(TaskDBSchema.Admin.Cols.PASSWORD));
        String usernamee = getString(getColumnIndex(TaskDBSchema.Admin.Cols.USERNAME));



        Admin admin = new Admin(UUID.fromString(strUUID));
        admin.setmPassword(password);
        admin.setmUserName(usernamee);
        return admin;
    }
}
