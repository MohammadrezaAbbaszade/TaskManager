package com.example.completetask.model;

import java.util.UUID;

public class User {
    private String mUserName;
    private String mPassword;
    private UUID mUUID;

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public User()
    {
        mUUID=UUID.randomUUID();
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
