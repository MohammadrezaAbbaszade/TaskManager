package com.example.completetask.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class User {
    @Unique
    private String mUserName;
    private String mPassword;
    private String mTimeRegister;
    @Id(autoincrement = true)
    private Long mID;
    @Generated(hash = 357683935)
    public User(String mUserName, String mPassword, String mTimeRegister,
            Long mID) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mTimeRegister = mTimeRegister;
        this.mID = mID;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getMUserName() {
        return this.mUserName;
    }
    public void setMUserName(String mUserName) {
        this.mUserName = mUserName;
    }
    public String getMPassword() {
        return this.mPassword;
    }
    public void setMPassword(String mPassword) {
        this.mPassword = mPassword;
    }
    public String getMTimeRegister() {
        return this.mTimeRegister;
    }
    public void setMTimeRegister(String mTimeRegister) {
        this.mTimeRegister = mTimeRegister;
    }
    public Long getMID() {
        return this.mID;
    }
    public void setMID(Long mID) {
        this.mID = mID;
    }

}
