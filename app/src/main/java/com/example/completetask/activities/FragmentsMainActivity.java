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
import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

public class FragmentsMainActivity extends AppCompatActivity {
    private static final String USER_OF_USER = "com.example.completetask.id of user";
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private String usernameOfUser;

    public static Intent newIntent(Context context, String usernameOfUser) {
        Intent intent = new Intent(context, FragmentsMainActivity.class);
        intent.putExtra(USER_OF_USER, usernameOfUser);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        usernameOfUser = getIntent().getStringExtra(USER_OF_USER);
        mViewPager = findViewById(R.id.pager);

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                if (position == 0)
                    return TodoListFragment.newInstance(usernameOfUser);
                else if (position == 1)
                    return DoingListFragment.newInstance(usernameOfUser);
                else
                    return DoneListFragment.newInstance(usernameOfUser);
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0)
                    return "TODO";
                else if (position == 1)
                    return "DOING";
                else
                    return "DONE";
            }
        });
        mTabLayout = findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
