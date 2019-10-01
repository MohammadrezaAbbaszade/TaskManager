package com.example.completetask.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.completetask.fragments.AdminSignUpFragment;
import com.example.completetask.fragments.SignUpFragment;
import com.example.completetask.model.User;

public class AdminSignUpActivity extends SingleActivity {
    private static final String VALUE_FROM_LOGIN_FRAGMENT = "com.example.completetask.values_from_LoginFragment";
    public static Intent newIntent(Context context, String[] valuesFromAdminFragment)
    {
        Intent intent=new Intent(context,AdminSignUpActivity.class);
        intent.putExtra(VALUE_FROM_LOGIN_FRAGMENT,valuesFromAdminFragment);
        return intent;
    }

    @Override
    public Fragment onCreatFragment() {
        String[] valueForAdminSignUPfragment=getIntent().getStringArrayExtra(VALUE_FROM_LOGIN_FRAGMENT);
        return AdminSignUpFragment.newInstance(valueForAdminSignUPfragment);
    }
}
