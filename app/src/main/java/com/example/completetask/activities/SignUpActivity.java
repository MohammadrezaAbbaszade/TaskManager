package com.example.completetask.activities;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.example.completetask.fragments.SignUpFragment;

public class SignUpActivity extends SingleActivity {

    private static final String VALUE_FROM_LOGIN_FRAGMENT = "com.example.completetask.values_from_LoginFragment";

    public static Intent newIntent(Context context, String[] valuesFromLoginFragment) {
        Intent intent = new Intent(context, SignUpActivity.class);
        intent.putExtra(VALUE_FROM_LOGIN_FRAGMENT,valuesFromLoginFragment);
        return intent;
    }

    @Override
    public Fragment onCreatFragment() {
        String[] valueForSignUPfragment=getIntent().getStringArrayExtra(VALUE_FROM_LOGIN_FRAGMENT);
        return SignUpFragment.newInstance(valueForSignUPfragment);
    }
}
