package com.example.completetask.model;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.completetask.database.TaskDBSchema;

import java.util.Date;
import java.util.UUID;

public class ToDoCursorWrapper extends CursorWrapper {
    public ToDoCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public ToDo getToDo() {
        String strUUID = getString(getColumnIndex(TaskDBSchema.ToDo.Cols.UUID));
        String title = getString(getColumnIndex(TaskDBSchema.ToDo.Cols.TITLE));
        String date = getString(getColumnIndex(TaskDBSchema.ToDo.Cols.DATE));
        String time = getString(getColumnIndex(TaskDBSchema.ToDo.Cols.TIME));
        boolean mToDoCheckBox = getInt(getColumnIndex(TaskDBSchema.ToDo.Cols.TODOCHECKBOX)) == 1;
        boolean mDoingCheckBox = getInt(getColumnIndex(TaskDBSchema.ToDo.Cols.DOINGCHECKBOX)) == 1;
        boolean mDoneCheckBox = getInt(getColumnIndex(TaskDBSchema.ToDo.Cols.DONECHECKBOX)) == 1;
        String discription = getString(getColumnIndex(TaskDBSchema.ToDo.Cols.DISCRIPTION));
        String usernamee = getString(getColumnIndex(TaskDBSchema.ToDo.Cols.USERNAME));


        ToDo toDo = new ToDo(UUID.fromString(strUUID));
        toDo.setTitle(title);
        toDo.setDate(date);
        toDo.setToDo(mToDoCheckBox);
        toDo.setDoing(mDoingCheckBox);
        toDo.setDone(mDoneCheckBox);
        toDo.setUserName(usernamee);
        toDo.setDiscriptin(discription);
        toDo.setTime(time);
        return toDo;
    }
}
