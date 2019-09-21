package com.example.completetask.model;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class ToDo {
    private UUID id;
    private String mTitle;
    private String mDiscriptin;
    private String mUserName;
    private String mDate;
    private String mTime;
    private boolean mIsToDo;
    private boolean mIsDoing;
    private boolean mIsDone;

    public boolean isDone() {
        return mIsDone;
    }

    public void setDone(boolean done) {
        mIsDone = done;
    }

    public boolean isDoing() {
        return mIsDoing;
    }

    public void setDoing(boolean doing) {
        mIsDoing = doing;
    }

    public ToDo() {
        id = UUID.randomUUID();

    }

    public UUID getId() {
        return id;
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

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
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

    public boolean isToDo() {
        return mIsToDo;
    }

    public void setToDo(boolean toDo) {
        mIsToDo = toDo;
    }
}