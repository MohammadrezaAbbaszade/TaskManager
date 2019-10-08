package com.example.completetask.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Admin {
    @Unique
    private String mUserName;
    private String mPassword;
    @Id(autoincrement = true)
    private Long mID;
    @Generated(hash = 772522839)
    public Admin(String mUserName, String mPassword, Long mID) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mID = mID;
    }
    @Generated(hash = 1708792177)
    public Admin() {
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
    public Long getMID() {
        return this.mID;
    }
    public void setMID(Long mID) {
        this.mID = mID;
    }

}
