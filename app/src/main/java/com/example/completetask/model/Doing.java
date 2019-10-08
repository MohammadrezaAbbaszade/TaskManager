package com.example.completetask.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Doing {
    @Id(autoincrement = true)
    private Long mID;
    private String mTitle;
    private String mDiscriptin;
    private String mDate;
    private String mTime;
    @Unique
    private String mUserName;
    private boolean mIsDoing;
    private boolean mIsDone;
    private boolean mIsToDo;
    @Generated(hash = 157457274)
    public Doing(Long mID, String mTitle, String mDiscriptin, String mDate,
            String mTime, String mUserName, boolean mIsDoing, boolean mIsDone,
            boolean mIsToDo) {
        this.mID = mID;
        this.mTitle = mTitle;
        this.mDiscriptin = mDiscriptin;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mUserName = mUserName;
        this.mIsDoing = mIsDoing;
        this.mIsDone = mIsDone;
        this.mIsToDo = mIsToDo;
    }
    @Generated(hash = 1922873774)
    public Doing() {
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
    public String getMUserName() {
        return this.mUserName;
    }
    public void setMUserName(String mUserName) {
        this.mUserName = mUserName;
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
    public boolean getMIsToDo() {
        return this.mIsToDo;
    }
    public void setMIsToDo(boolean mIsToDo) {
        this.mIsToDo = mIsToDo;
    }

}