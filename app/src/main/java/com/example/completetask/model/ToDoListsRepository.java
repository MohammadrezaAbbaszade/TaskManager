package com.example.completetask.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToDoListsRepository {
    List<ToDo> mToDoes;
    private static ToDoListsRepository ourInstance;

    private ToDoListsRepository() {
        mToDoes = new ArrayList<>();
    }

    public static ToDoListsRepository getInstance() {
        if (ourInstance == null) {
            ourInstance = new ToDoListsRepository();
        }
        return ourInstance;
    }

    public List<ToDo> getToDoes(String username) {
        List<ToDo> mToDoList=new ArrayList<>();
        for (ToDo toDo : mToDoes)
            if (toDo.getUserName().equals(username))
              mToDoList.add(toDo);


       return mToDoList;
    }

    public ToDo getToDo(UUID id) {
        for (ToDo toDo : mToDoes)
            if (toDo.getId().equals(id))
                return toDo;


        return null;

    }

    public void insertToDo(ToDo toDo) {
        mToDoes.add(toDo);
    }

    public void updateToDo(ToDo toDo) throws Exception {
        ToDo t = getToDo(toDo.getId());
        if (t == null)
            throw new Exception("this crime does not exist");
        t.setTitle(toDo.getTitle());
        t.setDiscriptin(toDo.getDiscriptin());
        t.setDate(toDo.getDate());
        t.setToDo(toDo.isToDo());
        t.setTime(toDo.getTime());
    }

    public void deleteToDo(ToDo toDo) throws Exception {
        ToDo t = getToDo(toDo.getId());
        if (t == null)
            throw new Exception("this crime does not exist");
        mToDoes.remove(t);

    }
}
