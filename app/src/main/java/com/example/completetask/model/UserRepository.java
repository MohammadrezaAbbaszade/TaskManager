package com.example.completetask.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {
    private static UserRepository reposity;
    private List<User> mUserList;

    private UserRepository() {
        mUserList = new ArrayList<>();
    }

    public static UserRepository getInstance() {
        if (reposity == null) {
            reposity = new UserRepository();
        }
        return reposity;
    }

    public void addUser(User user) {
        if (mUserList.contains(user)) {
            throw new IllegalArgumentException("This UserName Is Exist!");
        }

        this.mUserList.add(user);
    }
    public boolean checkUserName(String username) {
        for (User user : mUserList) {
            if (user.getmUserName().equals(username))
                return true;
        }
        return false;
    }
    public boolean login(String username, String password) {
        for (User user : mUserList) {
            if (user.getmUserName().equals(username) &&
                    user.getmPassword().equals(password))
                return true;
        }
        return false;
    }
    public List<User> getmUsers() {
        return mUserList;
    }
    public User getUser(UUID uuid)
    {
        for(User user:mUserList)
            if(user.getUUID().equals(uuid))
                return user;


        return null;

    }
    public int getPosition(UUID id)
    {
        return mUserList.indexOf(getUser(id));
    }
    public void deleteCrime(User user) throws Exception {
        User u=getUser(user.getUUID());
        if(u==null)
            throw new Exception("this user does not exist");
       mUserList.remove(u);
    }
}
