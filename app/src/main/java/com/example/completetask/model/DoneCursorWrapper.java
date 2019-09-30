package com.example.completetask.model;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.completetask.database.TaskDBSchema;

import java.util.UUID;

public class DoneCursorWrapper extends CursorWrapper {
    public DoneCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Done getDone() {
        String strUUID = getString(getColumnIndex(TaskDBSchema.Done.Cols.UUID));
        String title = getString(getColumnIndex(TaskDBSchema.Done.Cols.TITLE));
        String date = getString(getColumnIndex(TaskDBSchema.Done.Cols.DATE));
        String time = getString(getColumnIndex(TaskDBSchema.Done.Cols.TIME));
        boolean mToDoCheckBox = getInt(getColumnIndex(TaskDBSchema.Done.Cols.TODOCHECKBOX)) == 1;
        boolean mDoingCheckBox = getInt(getColumnIndex(TaskDBSchema.Done.Cols.DOINGCHECKBOX)) == 1;
        boolean mDoneCheckBox = getInt(getColumnIndex(TaskDBSchema.Done.Cols.DONECHECKBOX)) == 1;
        String discription = getString(getColumnIndex(TaskDBSchema.Done.Cols.DISCRIPTION));
        String usernamee = getString(getColumnIndex(TaskDBSchema.Done.Cols.USERNAME));


        Done done = new Done(UUID.fromString(strUUID));
        done.setTitle(title);
        done.setDate(date);
        done.setToDo(mToDoCheckBox);
        done.setDoing(mDoingCheckBox);
        done.setDone(mDoneCheckBox);
        done.setUserName(usernamee);
        done.setDiscriptin(discription);
        done.setTime(time);
        return done;
    }
}
