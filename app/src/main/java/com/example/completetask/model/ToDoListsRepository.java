package com.example.completetask.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.completetask.database.TaskDBApplication;
import com.example.completetask.database.TaskOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToDoListsRepository {
    private static ToDoListsRepository ourInstance;
    private DaoSession daoSession;
    private ToDoDao toDoDao;


    private ToDoListsRepository() {
        daoSession = TaskDBApplication.getInstance().getDaoSession();
        toDoDao = daoSession.getToDoDao();
    }

    public static ToDoListsRepository getInstance() {
        if (ourInstance == null) {
            ourInstance = new ToDoListsRepository();
        }
        return ourInstance;
    }

    public List<ToDo> getToDoes(String username) {
        return toDoDao.queryBuilder()
                .where(ToDoDao.Properties.MUserName.eq(username)).list();
    }

    public ToDo getToDo(Long uuid) {

        return toDoDao.queryBuilder()
                .where(ToDoDao.Properties.MID.eq(uuid))
                .unique();
    }

    public void insertToDo(ToDo toDo) {
        toDoDao.insert(toDo);
    }

    public void updateToDo(ToDo toDo) throws Exception {
        toDoDao.update(toDo);
    }

    public void deleteToDo(ToDo toDo) throws Exception {
        ToDo t = getToDo(toDo.getMID());
        if (t == null)
            throw new Exception("This task does not exist!!!");
        toDoDao.delete(t);
    }

    public void deleteToDoes(List<ToDo> toDos) throws Exception {
        for (ToDo t : toDos) {
            ToDo t2 = getToDo(t.getMID());
            if (t2 == null)
                throw new Exception("You Dont Have Any Tasks  To Delete!!!");
            toDoDao.deleteByKey(t.getMID());
        }

    }

}
