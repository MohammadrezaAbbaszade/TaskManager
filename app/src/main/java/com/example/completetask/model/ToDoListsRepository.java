package com.example.completetask.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.completetask.database.TaskDBSchema;
import com.example.completetask.database.TaskOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ToDoListsRepository {
    private static ToDoListsRepository ourInstance;
    private SQLiteDatabase mDatabase;
    private Context mContext;


    private ToDoListsRepository(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TaskOpenHelper(mContext).getWritableDatabase();
    }
    public static ToDoListsRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new ToDoListsRepository(context);
        }
        return ourInstance;
    }
    private ToDoCursorWrapper queryToDo(String where, String[] whereArgs) {
        Cursor cursor= mDatabase.query(TaskDBSchema.ToDo.NAME,
                null,
                where,
                whereArgs,
                null,
                null,
                null);
        return new ToDoCursorWrapper(cursor);
    }

    public List<ToDo> getToDoes(String username) {
        List<ToDo> toDoList = new ArrayList<>();

       ToDoCursorWrapper cursor = queryToDo(null, null);

        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                toDoList.add(cursor.getToDo());

                cursor.moveToNext();
            }

        } finally {
            cursor.close();
        }
        List<ToDo> mToDoList = new ArrayList<>();
        for (ToDo toDo : toDoList)
            if (toDo.getUserName().equals(username))
                mToDoList.add(toDo);


        return mToDoList;
    }

    public ToDo getToDo(UUID uuid) {
        String[] whereArgs = new String[]{uuid.toString()};
        ToDoCursorWrapper cursor = queryToDo(TaskDBSchema.ToDo.Cols.UUID + " = ?", whereArgs);

        try {
            if (cursor == null || cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getToDo();

        } finally {
            cursor.close();
        }
    }

    public void insertToDo(ToDo toDo) {
        ContentValues values = getContentValues(toDo);
        mDatabase.insertOrThrow(TaskDBSchema.ToDo.NAME, null, values);
    }

    public void updateToDo(ToDo toDo) throws Exception {
        ContentValues values = getContentValues(toDo);
        String where = TaskDBSchema.ToDo.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{toDo.getId().toString()};
        mDatabase.update(TaskDBSchema.ToDo.NAME, values, where, whereArgs);
    }

    public void deleteToDo(ToDo toDo) throws Exception {
        ToDo t = getToDo(toDo.getId());
        if (t == null)
            throw new Exception("This crime does not exist!!!");
        String where = TaskDBSchema.ToDo.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{toDo.getId().toString()};
        mDatabase.delete(TaskDBSchema.ToDo.NAME, where, whereArgs);
    }
    public void deleteToDoes(List<ToDo> toDos) throws Exception {
       for(ToDo t:toDos) {
           ToDo t2=getToDo(t.getId());
           if (t2 == null)
               throw new Exception("You Dont Have Any Tasks  To Delete!!!");
           String where = TaskDBSchema.ToDo.Cols.UUID + " = ?";
           String[] whereArgs = new String[]{t.getId().toString()};
           mDatabase.delete(TaskDBSchema.ToDo.NAME, where, whereArgs);
       }

    }
    private ContentValues getContentValues(ToDo toDo) {
        ContentValues values = new ContentValues();
        values.put(TaskDBSchema.ToDo.Cols.UUID, toDo.getId().toString());
        values.put(TaskDBSchema.ToDo.Cols.TITLE, toDo.getTitle());
        values.put(TaskDBSchema.ToDo.Cols.USERNAME, toDo.getUserName());
        values.put(TaskDBSchema.ToDo.Cols.DATE, toDo.getDate());
        values.put(TaskDBSchema.ToDo.Cols.TIME, toDo.getTime());
        values.put(TaskDBSchema.ToDo.Cols.DOINGCHECKBOX, toDo.isDoing() ? 1 : 0);
        values.put(TaskDBSchema.ToDo.Cols.DONECHECKBOX, toDo.isDone() ? 1 : 0);
        values.put(TaskDBSchema.ToDo.Cols.TODOCHECKBOX, toDo.isToDo() ? 1 : 0);
        values.put(TaskDBSchema.ToDo.Cols.DISCRIPTION, toDo.getDiscriptin());

        return values;
    }
}
