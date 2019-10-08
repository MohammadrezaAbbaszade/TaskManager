package com.example.completetask.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.completetask.model.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class TaskOpenHelper extends DaoMaster.DevOpenHelper {

    public TaskOpenHelper(Context context, String name) {
        super(context, name);
    }

    public TaskOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
