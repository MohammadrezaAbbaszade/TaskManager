package com.example.completetask.model;

import java.util.UUID;

public class Admin {
    private String mUserName;
    private String mPassword;
    private UUID mUUID;

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public Admin() {
        this(UUID.randomUUID());
    }

    public Admin(UUID uuid) {
        mUUID = uuid;
    }
    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
