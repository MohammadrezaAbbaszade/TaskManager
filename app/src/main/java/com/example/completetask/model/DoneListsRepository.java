package com.example.completetask.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoneListsRepository {
    List<Done> mDones;
    private static DoneListsRepository ourInstance;

    private DoneListsRepository() {
        mDones = new ArrayList<>();
    }

    public static DoneListsRepository getInstance() {
        if (ourInstance == null) {
            ourInstance = new DoneListsRepository();
        }
        return ourInstance;
    }

    public List<Done> getDones(String username) {
        List<Done> mDoneList = new ArrayList<>();
        for (Done done : mDones)
            if (done.getUserName().equals(username))
                mDoneList.add(done);


        return mDoneList;
    }

    public Done getDone(UUID id) {
        for (Done done : mDones)
            if (done.getUUID().equals(id))
                return done;


        return null;

    }

    public void insertDone(Done done) {
        mDones.add(done);
    }

    public void updateDone(Done done) throws Exception {
        Done d = getDone(done.getUUID());
        if (d == null)
            throw new Exception("this crime does not exist");
        d.setTitle(done.getTitle());
        d.setDiscriptin(done.getDiscriptin());
        d.setDate(done.getDate());
        d.setDone(done.isDone());
        d.setTime(done.getTime());
    }

    public void deleteDone(Done done) throws Exception {
        Done d = getDone(done.getUUID());
        if (d == null)
            throw new Exception("this crime does not exist");
        mDones.remove(d);

    }
}
