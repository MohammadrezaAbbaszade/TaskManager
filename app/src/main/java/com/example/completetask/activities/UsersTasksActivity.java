package com.example.completetask.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.completetask.fragments.UsersTasksFragment;

public class UsersTasksActivity extends SingleActivity {
    private static final String USER_OF_USER = "com.example.completetask.id of user";
    public static Intent newIntent(Context context, String usernameOfUser) {
        Intent intent = new Intent(context, UsersTasksActivity.class);
        intent.putExtra(USER_OF_USER, usernameOfUser);
        return intent;
    }

    @Override
    public Fragment onCreatFragment() {
        String userName=getIntent().getStringExtra(USER_OF_USER);
        return UsersTasksFragment.newInstance(userName);
    }
}
