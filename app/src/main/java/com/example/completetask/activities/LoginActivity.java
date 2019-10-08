package com.example.completetask.activities;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.example.completetask.fragments.LoginFragment;
import com.example.completetask.model.User;

import java.util.UUID;

public class LoginActivity extends SingleActivity {

    private static final String VALUE_FROM_SIGN_UP_FRAGMENT = "com.example.completetask.value_from_sign_up_fragment";
    private static final String USER_FROM_SIGN_UP_FRAGMENT = "com.example.completetask.id_from_sign_up_fragment";

    public static Intent newIntent(Context context, String[] valueFromSignUpFragment, User user)
{
    Intent intent=new Intent(context,LoginActivity.class);
    intent.putExtra(VALUE_FROM_SIGN_UP_FRAGMENT,valueFromSignUpFragment);
    intent.putExtra(USER_FROM_SIGN_UP_FRAGMENT,user.getMUserName());
    return intent;
}

    @Override
    public Fragment onCreatFragment() {
        String[] valueForLoginFragment=getIntent().getStringArrayExtra(VALUE_FROM_SIGN_UP_FRAGMENT);
        String userNameFromSignUpFragment=getIntent().getStringExtra(USER_FROM_SIGN_UP_FRAGMENT);
        return LoginFragment.newInstance(valueForLoginFragment,userNameFromSignUpFragment);
    }
}
