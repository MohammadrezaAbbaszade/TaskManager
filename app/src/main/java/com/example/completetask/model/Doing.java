package com.example.completetask.model;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Doing {
    private String mTitle;
    private String mDiscriptin;
    private UUID mUUID;
    private String mDate;
    private String mTime;
    private String mUserName;
    private boolean mIsDoing;
    private boolean mIsDone;
    private boolean mIsToDo;

    public boolean isDone() {
        return mIsDone;
    }

    public void setDone(boolean done) {
        mIsDone = done;
    }

    public boolean isToDo() {
        return mIsToDo;
    }

    public void setToDo(boolean toDo) {
        mIsToDo = toDo;
    }

    public Doing() {
        this(UUID.randomUUID());
    }

    public Doing(UUID uuid) {
        mUUID = uuid;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDiscriptin() {
        return mDiscriptin;
    }

    public void setDiscriptin(String discriptin) {
        mDiscriptin = discriptin;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public boolean isDoing() {
        return mIsDoing;
    }

    public void setDoing(boolean doing) {
        mIsDoing = doing;
    }
}