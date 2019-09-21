package com.example.completetask.model;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Done {
    private String mTitle;
    private String mDiscriptin;
    private String mUserName;
    private UUID mUUID;
    private String mDate;
    private String mTime;
    private boolean mIsDone;
    private boolean mIsToDo;
    private boolean mIsDoing;

    public boolean isToDo() {
        return mIsToDo;
    }

    public void setToDo(boolean toDo) {
        mIsToDo = toDo;
    }

    public boolean isDoing() {
        return mIsDoing;
    }

    public void setDoing(boolean doing) {
        mIsDoing = doing;
    }

    public Done() {
        mUUID = UUID.randomUUID();
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

    public boolean isDone() {
        return mIsDone;
    }

    public void setDone(boolean done) {
        mIsDone = done;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }
}