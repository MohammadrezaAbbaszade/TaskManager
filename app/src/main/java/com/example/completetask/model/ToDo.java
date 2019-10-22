package com.example.completetask.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class ToDo {
    @Id(autoincrement = true)
    private Long mID;
    private String mTitle;
    private String mDiscriptin;
    private String mUserName;
    private String mDate;
    private String mTime;
    private boolean mIsToDo;
    private boolean mIsDoing;
    private boolean mIsDone;
    @Generated(hash = 1680733557)
    public ToDo(Long mID, String mTitle, String mDiscriptin, String mUserName,
            String mDate, String mTime, boolean mIsToDo, boolean mIsDoing,
            boolean mIsDone) {
        this.mID = mID;
        this.mTitle = mTitle;
        this.mDiscriptin = mDiscriptin;
        this.mUserName = mUserName;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mIsToDo = mIsToDo;
        this.mIsDoing = mIsDoing;
        this.mIsDone = mIsDone;
    }
    @Generated(hash = 1151496819)
    public ToDo() {
    }
    public Long getMID() {
        return this.mID;
    }
    public void setMID(Long mID) {
        this.mID = mID;
    }
    public String getMTitle() {
        return this.mTitle;
    }
    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public String getMDiscriptin() {
        return this.mDiscriptin;
    }
    public void setMDiscriptin(String mDiscriptin) {
        this.mDiscriptin = mDiscriptin;
    }
    public String getMUserName() {
        return this.mUserName;
    }
    public void setMUserName(String mUserName) {
        this.mUserName = mUserName;
    }
    public String getMDate() {
        return this.mDate;
    }
    public void setMDate(String mDate) {
        this.mDate = mDate;
    }
    public String getMTime() {
        return this.mTime;
    }
    public void setMTime(String mTime) {
        this.mTime = mTime;
    }
    public boolean getMIsToDo() {
        return this.mIsToDo;
    }
    public void setMIsToDo(boolean mIsToDo) {
        this.mIsToDo = mIsToDo;
    }
    public boolean getMIsDoing() {
        return this.mIsDoing;
    }
    public void setMIsDoing(boolean mIsDoing) {
        this.mIsDoing = mIsDoing;
    }
    public boolean getMIsDone() {
        return this.mIsDone;
    }
    public void setMIsDone(boolean mIsDone) {
        this.mIsDone = mIsDone;
    }

}