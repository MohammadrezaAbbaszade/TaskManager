package com.example.completetask.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.completetask.R;
import com.example.completetask.fragments.AdminFragment;
import com.example.completetask.fragments.LoginFragment;
import com.example.completetask.model.Admin;
import com.example.completetask.model.User;

public class AdminActivity extends SingleActivity {
    private static final String VALUE_FROM_ADMIN_SIGN_UP_FRAGMENT = "com.example.completetask.activities.value_from_sign_up_fragment";
    private static final String USER_FROM_ADMIN_SIGN_UP_FRAGMENT = "com.example.completetask.activities.id_from_sign_up_fragment";
    public static Intent newIntent(Context context,String[] valueFromAdminSignUpFragment, Admin admin)
    {
        Intent intent=new Intent(context,AdminActivity.class);
        intent.putExtra(VALUE_FROM_ADMIN_SIGN_UP_FRAGMENT,valueFromAdminSignUpFragment);
        intent.putExtra(USER_FROM_ADMIN_SIGN_UP_FRAGMENT,admin.getmUserName());
        return intent;
    }


    @Override
    public Fragment onCreatFragment() {
        String[] valueForAdminFragment=getIntent().getStringArrayExtra(VALUE_FROM_ADMIN_SIGN_UP_FRAGMENT);
        String userNameFromAdminSignUpFragment=getIntent().getStringExtra(USER_FROM_ADMIN_SIGN_UP_FRAGMENT);
        return AdminFragment.newInstance(valueForAdminFragment,userNameFromAdminSignUpFragment);
    }

}
