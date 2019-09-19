package com.example.completetask.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoingListsRepository {
    List<Doing> mDoings;
    private static DoingListsRepository ourInstance;

    private DoingListsRepository() {
        mDoings = new ArrayList<>();
    }

    public static DoingListsRepository getInstance() {
        if (ourInstance == null) {
            ourInstance = new DoingListsRepository();
        }
        return ourInstance;
    }

    public List<Doing> getDoings(String username) {
        List<Doing> mDoingList = new ArrayList<>();
        for (Doing doing : mDoings)
            if (doing.getUserName().equals(username))
                mDoingList.add(doing);


        return mDoingList;
    }

    public Doing getDoing(UUID id) {
        for (Doing doing : mDoings)
            if (doing.getUUID().equals(id))
                return doing;


        return null;

    }

    public void insertDoing(Doing doing) {
        mDoings.add(doing);
    }

    public void updateDoing(Doing doing) throws Exception {
        Doing d = getDoing(doing.getUUID());
        if (d == null)
            throw new Exception("this crime does not exist");
        d.setTitle(doing.getTitle());
        d.setDiscriptin(doing.getDiscriptin());
        d.setDate(doing.getDate());
        d.setDoing(doing.isDoing());
        d.setTime(doing.getTime());
    }

    public void deleteDoing(Doing doing) throws Exception {
        Doing d = getDoing(doing.getUUID());
        if (d == null)
            throw new Exception("this crime does not exist");
        mDoings.remove(d);

    }
}
