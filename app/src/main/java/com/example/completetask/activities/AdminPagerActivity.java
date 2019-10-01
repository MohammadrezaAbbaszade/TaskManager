package com.example.completetask.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.completetask.R;
import com.example.completetask.fragments.DoingListFragment;
import com.example.completetask.fragments.DoneListFragment;
import com.example.completetask.fragments.TodoListFragment;
import com.example.completetask.fragments.UsersListFragment;
import com.google.android.material.tabs.TabLayout;

public class AdminPagerActivity extends SingleActivity{
    private static final String USER_OF_ADMIN = "com.example.completetask.id of user";
    public static Intent newIntent(Context context, String usernameOfUser) {
        Intent intent = new Intent(context, AdminPagerActivity.class);
        intent.putExtra(USER_OF_ADMIN, usernameOfUser);
        return intent;
    }
    @Override
    public Fragment onCreatFragment() {
        return UsersListFragment.newInstance();
    }
}
