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

public class DoingListsRepository {
    private static DoingListsRepository ourInstance;
    private SQLiteDatabase mDatabase;
    private Context mContext;


    private DoingListsRepository(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TaskOpenHelper(mContext).getWritableDatabase();
    }

    public static DoingListsRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DoingListsRepository(context);
        }
        return ourInstance;
    }

    private DoingCursorWrapper queryDoing(String where, String[] whereArgs) {
        Cursor cursor= mDatabase.query(TaskDBSchema.Doing.NAME,
                null,
                where,
                whereArgs,
                null,
                null,
                null);
        return new DoingCursorWrapper(cursor);
    }

    public List<Doing> getDoings(String username) {
        List<Doing> doingList = new ArrayList<>();

        DoingCursorWrapper cursor = queryDoing(null, null);

        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                doingList.add(cursor.getDoing());

                cursor.moveToNext();
            }

        } finally {
            cursor.close();
        }
        List<Doing> mDoingList = new ArrayList<>();
        for (Doing doing : doingList)
            if (doing.getUserName().equals(username))
                mDoingList.add(doing);


        return mDoingList;
    }

    public Doing getDoing(UUID uuid) {
        String[] whereArgs = new String[]{uuid.toString()};
        DoingCursorWrapper cursor = queryDoing(TaskDBSchema.Doing.Cols.UUID + " = ?", whereArgs);

        try {
            if (cursor == null || cursor.getCount() == 0)
                return null;

            cursor.moveToFirst();


            return cursor.getDoing();

        } finally {
            cursor.close();
        }
    }

    public void insertDoing(Doing doing) {
        ContentValues values = getContentValues(doing);
        mDatabase.insertOrThrow(TaskDBSchema.Doing.NAME, null, values);
    }

    public void updateDoing(Doing doing) throws Exception {
        ContentValues values = getContentValues(doing);
        String where = TaskDBSchema.Doing.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{doing.getUUID().toString()};
        mDatabase.update(TaskDBSchema.Doing.NAME, values, where, whereArgs);
    }

    public void deleteDoing(Doing doing) throws Exception {
        Doing d = getDoing(doing.getUUID());
        if (d == null)
            throw new Exception("This crime does not exist!!!");
        String where = TaskDBSchema.Doing.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{doing.getUUID().toString()};
        mDatabase.delete(TaskDBSchema.Doing.NAME, where, whereArgs);
    }
    public void deleteDoings(List<Doing> doings) throws Exception {
        for (Doing d : doings) {
            Doing d2 = getDoing(d.getUUID());
            if (d2 == null)
                throw new Exception("You Dont Have Any Tasks  To Delete!!!");
            String where = TaskDBSchema.Doing.Cols.UUID + " = ?";
            String[] whereArgs = new String[]{d.getUUID().toString()};
            mDatabase.delete(TaskDBSchema.Doing.NAME, where, whereArgs);
        }
    }
    private ContentValues getContentValues(Doing doing) {
        ContentValues values = new ContentValues();
        values.put(TaskDBSchema.Doing.Cols.UUID, doing.getUUID().toString());
        values.put(TaskDBSchema.Doing.Cols.TITLE, doing.getTitle());
        values.put(TaskDBSchema.Doing.Cols.USERNAME, doing.getUserName());
        values.put(TaskDBSchema.Doing.Cols.DATE, doing.getDate());
        values.put(TaskDBSchema.Doing.Cols.TIME, doing.getTime());
        values.put(TaskDBSchema.Doing.Cols.DOINGCHECKBOX, doing.isDoing() ? 1 : 0);
        values.put(TaskDBSchema.Doing.Cols.DONECHECKBOX, doing.isDone() ? 1 : 0);
        values.put(TaskDBSchema.Doing.Cols.TODOCHECKBOX, doing.isToDo() ? 1 : 0);
        values.put(TaskDBSchema.Doing.Cols.DISCRIPTION, doing.getDiscriptin());

        return values;
    }
}
