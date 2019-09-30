package com.example.completetask.model;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.completetask.database.TaskDBSchema;

import java.util.UUID;

public class DoingCursorWrapper extends CursorWrapper {
    public DoingCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Doing getDoing() {
        String strUUID = getString(getColumnIndex(TaskDBSchema.Doing.Cols.UUID));
        String title = getString(getColumnIndex(TaskDBSchema.Doing.Cols.TITLE));
        String date = getString(getColumnIndex(TaskDBSchema.Doing.Cols.DATE));
        String time = getString(getColumnIndex(TaskDBSchema.Doing.Cols.TIME));
        boolean mToDoCheckBox = getInt(getColumnIndex(TaskDBSchema.Doing.Cols.TODOCHECKBOX)) == 1;
        boolean mDoingCheckBox = getInt(getColumnIndex(TaskDBSchema.Doing.Cols.DOINGCHECKBOX)) == 1;
        boolean mDoneCheckBox =getInt(getColumnIndex(TaskDBSchema.Doing.Cols.DONECHECKBOX)) == 1;
        String discription = getString(getColumnIndex(TaskDBSchema.Doing.Cols.DISCRIPTION));
        String usernamee =getString(getColumnIndex(TaskDBSchema.Doing.Cols.USERNAME));


        Doing doing = new Doing(UUID.fromString(strUUID));
        doing.setTitle(title);
        doing.setDate(date);
        doing.setToDo(mToDoCheckBox);
        doing.setDoing(mDoingCheckBox);
        doing.setDone(mDoneCheckBox);
        doing.setUserName(usernamee);
        doing.setDiscriptin(discription);
        doing.setTime(time);

        return doing;
    }
}
