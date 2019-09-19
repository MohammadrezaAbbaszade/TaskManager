package com.example.completetask.activities;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.completetask.R;

public abstract class SingleActivity extends AppCompatActivity {
    public abstract Fragment onCreatFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_activity);
        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.container);
        if(fragment==null)
        {
            fm.beginTransaction().replace(R.id.container,onCreatFragment()).commit();

        }
    }
}
