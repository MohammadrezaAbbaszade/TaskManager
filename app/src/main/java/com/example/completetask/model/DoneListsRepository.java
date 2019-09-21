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

public class DoneListsRepository {
    private static DoneListsRepository ourInstance;
    private SQLiteDatabase mDatabase;
    private Context mContext;


    private DoneListsRepository(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TaskOpenHelper(mContext).getWritableDatabase();
    }

    public static DoneListsRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DoneListsRepository(context);
        }
        return ourInstance;
    }

    private Cursor queryDone(String where, String[] whereArgs) {
        return mDatabase.query(TaskDBSchema.Done.NAME,
                null,
                where,
                whereArgs,
                null,
                null,
                null);
    }

    public List<Done> getDones(String username) {
        List<Done> doneList = new ArrayList<>();

        Cursor cursor = queryDone(null, null);

        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                String strUUID = cursor.getString(cursor.getColumnIndex(TaskDBSchema.Done.Cols.UUID));
                String title = cursor.getString(cursor.getColumnIndex(TaskDBSchema.Done.Cols.TITLE));
                String date = cursor.getString(cursor.getColumnIndex(TaskDBSchema.Done.Cols.DATE));
                String time = cursor.getString(cursor.getColumnIndex(TaskDBSchema.Done.Cols.TIME));
                boolean mToDoCheckBox = cursor.getInt(cursor.getColumnIndex(TaskDBSchema.Done.Cols.TODOCHECKBOX)) == 1;
                boolean mDoingCheckBox = cursor.getInt(cursor.getColumnIndex(TaskDBSchema.Done.Cols.DOINGCHECKBOX)) == 1;
                boolean mDoneCheckBox = cursor.getInt(cursor.getColumnIndex(TaskDBSchema.Done.Cols.DONECHECKBOX)) == 1;
                String discription = cursor.getString(cursor.getColumnIndex(TaskDBSchema.Done.Cols.DISCRIPTION));
                String usernamee = cursor.getString(cursor.getColumnIndex(TaskDBSchema.Done.Cols.USERNAME));


                Done done = new Done(UUID.fromString(strUUID));
                done.setTitle(title);
                done.setDate(date);
                done.setToDo(mToDoCheckBox);
                done.setDoing(mDoingCheckBox);
                done.setDone(mDoneCheckBox);
                done.setUserName(usernamee);
                done.setDiscriptin(discription);
                done.setTime(time);
                doneList.add(done);

                cursor.moveToNext();
            }

        } finally {
            cursor.close();
        }
        List<Done> mDoneList = new ArrayList<>();
        for (Done done : doneList)
            if (done.getUserName().equals(username))
                mDoneList.add(done);


        return mDoneList;
    }

    public Done getDone(UUID uuid) {
        String[] whereArgs = new String[]{uuid.toString()};
        Cursor cursor = queryDone(TaskDBSchema.Done.Cols.UUID + " = ?", whereArgs);

        try {
            if (cursor == null || cursor.getCount() == 0)
                return null;

            cursor.moveToFirst();

            String strUUID = cursor.getString(cursor.getColumnIndex(TaskDBSchema.Done.Cols.UUID));
            String title = cursor.getString(cursor.getColumnIndex(TaskDBSchema.Done.Cols.TITLE));
            String date = cursor.getString(cursor.getColumnIndex(TaskDBSchema.Done.Cols.DATE));
            String time = cursor.getString(cursor.getColumnIndex(TaskDBSchema.Done.Cols.TIME));
            boolean mToDoCheckBox = cursor.getInt(cursor.getColumnIndex(TaskDBSchema.Done.Cols.TODOCHECKBOX)) == 1;
            boolean mDoingCheckBox = cursor.getInt(cursor.getColumnIndex(TaskDBSchema.Done.Cols.DOINGCHECKBOX)) == 1;
            boolean mDoneCheckBox = cursor.getInt(cursor.getColumnIndex(TaskDBSchema.Done.Cols.DONECHECKBOX)) == 1;
            String discription = cursor.getString(cursor.getColumnIndex(TaskDBSchema.Done.Cols.DISCRIPTION));
            String usernamee = cursor.getString(cursor.getColumnIndex(TaskDBSchema.Done.Cols.USERNAME));

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

        } finally {
            cursor.close();
        }
    }

    public void insertDone(Done done) {
        ContentValues values = getContentValues(done);
        mDatabase.insertOrThrow(TaskDBSchema.Done.NAME, null, values);
    }

    public void updateDone(Done done) throws Exception {
        ContentValues values = getContentValues(done);
        String where = TaskDBSchema.Done.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{done.getUUID().toString()};
        mDatabase.update(TaskDBSchema.Done.NAME, values, where, whereArgs);
    }

    public void deleteDone(Done done) throws Exception {
        Done d = getDone(done.getUUID());
        if (d == null)
            throw new Exception("This crime does not exist!!!");
        String where = TaskDBSchema.Done.Cols.UUID + " = ?";
        String[] whereArgs = new String[]{done.getUUID().toString()};
        mDatabase.delete(TaskDBSchema.Done.NAME, where, whereArgs);
    }

    private ContentValues getContentValues(Done done) {
        ContentValues values = new ContentValues();
        values.put(TaskDBSchema.Done.Cols.UUID, done.getUUID().toString());
        values.put(TaskDBSchema.Done.Cols.TITLE, done.getTitle());
        values.put(TaskDBSchema.Done.Cols.USERNAME, done.getUserName());
        values.put(TaskDBSchema.Done.Cols.DATE, done.getDate());
        values.put(TaskDBSchema.Done.Cols.TIME, done.getTime());
        values.put(TaskDBSchema.Done.Cols.DOINGCHECKBOX, done.isDoing() ? 1 : 0);
        values.put(TaskDBSchema.Done.Cols.DONECHECKBOX, done.isDone() ? 1 : 0);
        values.put(TaskDBSchema.Done.Cols.TODOCHECKBOX, done.isToDo() ? 1 : 0);
        values.put(TaskDBSchema.Done.Cols.DISCRIPTION, done.getDiscriptin());

        return values;
    }
}
