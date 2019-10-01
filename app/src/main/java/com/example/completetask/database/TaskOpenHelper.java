package com.example.completetask.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TaskOpenHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public TaskOpenHelper(@Nullable Context context) {
        super(context,  TaskDBSchema.NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TaskDBSchema.Doing.NAME + "(" +
                TaskDBSchema.Doing.Cols._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TaskDBSchema.Doing.Cols.UUID + "," +
                TaskDBSchema.Doing.Cols.TITLE + "," +
                TaskDBSchema.Doing.Cols.DATE + "," +
                TaskDBSchema.Doing.Cols.DISCRIPTION + "," +
                TaskDBSchema.Doing.Cols.TIME + "," +
                TaskDBSchema.Doing.Cols.DOINGCHECKBOX + "," +
                TaskDBSchema.Doing.Cols.DONECHECKBOX + "," +
                TaskDBSchema.Doing.Cols.TODOCHECKBOX + "," +
                TaskDBSchema.Doing.Cols.USERNAME +
                ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + TaskDBSchema.Done.NAME + "(" +
                TaskDBSchema.Done.Cols._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TaskDBSchema.Done.Cols.UUID + "," +
                TaskDBSchema.Done.Cols.TITLE + "," +
                TaskDBSchema.Done.Cols.DATE + "," +
                TaskDBSchema.Done.Cols.DISCRIPTION + "," +
                TaskDBSchema.Done.Cols.TIME + "," +
                TaskDBSchema.Done.Cols.DOINGCHECKBOX + "," +
                TaskDBSchema.Done.Cols.DONECHECKBOX + "," +
                TaskDBSchema.Done.Cols.TODOCHECKBOX + "," +
                TaskDBSchema.Done.Cols.USERNAME +
                ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + TaskDBSchema.ToDo.NAME + "(" +
                TaskDBSchema.ToDo.Cols._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TaskDBSchema.ToDo.Cols.UUID + "," +
                TaskDBSchema.ToDo.Cols.TITLE + "," +
                TaskDBSchema.ToDo.Cols.DATE + "," +
                TaskDBSchema.ToDo.Cols.DISCRIPTION + "," +
                TaskDBSchema.ToDo.Cols.TIME + "," +
                TaskDBSchema.ToDo.Cols.DOINGCHECKBOX + "," +
                TaskDBSchema.ToDo.Cols.DONECHECKBOX + "," +
                TaskDBSchema.ToDo.Cols.TODOCHECKBOX + "," +
                TaskDBSchema.ToDo.Cols.USERNAME +
                ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + TaskDBSchema.User.NAME + "(" +
                TaskDBSchema.User.Cols._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TaskDBSchema.User.Cols.UUID + "," +
                TaskDBSchema.User.Cols.USERNAME + "," +
                TaskDBSchema.User.Cols.PASSWORD + ","+
                TaskDBSchema.User.Cols.TIMETOREGISTER +
                ");");
        sqLiteDatabase.execSQL("CREATE TABLE " + TaskDBSchema.Admin.NAME + "(" +
                TaskDBSchema.Admin.Cols._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TaskDBSchema.Admin.Cols.UUID + "," +
                TaskDBSchema.Admin.Cols.USERNAME + "," +
                TaskDBSchema.Admin.Cols.PASSWORD +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
