package com.example.completetask.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.completetask.activities.FragmentsMainActivity;
import com.example.completetask.R;
import com.example.completetask.activities.SignUpActivity;
import com.example.completetask.model.UserRepository;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    public static final String LOGIN_VALUE = "com.example.completetask.LoginValue";
    public static final int REQUEST_CODE = 0;
    private static final String VALUE_FROM_SIGN_UP_FRAGMENT = "com.example.completetask.value_from_sign_up_fragment";
    private static final String USERNAME_FROM_SIGN_UP_FRAGMENT = "idFromSignUpFragment";
    private String[] result;
    private Button mButtonLogin;
    private Button mButtonSignUp;
    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    String[] valueFromSignUpFragment;
    private String username;

    public static LoginFragment newInstance(String[] valueFromSignUpFragment, String usernameFromSignUpFragment) {

        Bundle args = new Bundle();
        args.putStringArray(VALUE_FROM_SIGN_UP_FRAGMENT, valueFromSignUpFragment);
        args.putString(USERNAME_FROM_SIGN_UP_FRAGMENT, usernameFromSignUpFragment);
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        valueFromSignUpFragment = getArguments().getStringArray(VALUE_FROM_SIGN_UP_FRAGMENT);
        username =getArguments().getString(USERNAME_FROM_SIGN_UP_FRAGMENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        if (valueFromSignUpFragment != null) {
            mUserNameEditText.setText(valueFromSignUpFragment[0]);
            mPasswordEditText.setText(valueFromSignUpFragment[1]);
        }

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrname = mUserNameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String[] values = {usrname, password};
                Intent intent = SignUpActivity.newIntent(getActivity(), values);
                startActivity(intent);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrname = mUserNameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                if (UserRepository.getInstance().login(usrname, password)) {
                    Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    Intent intent = FragmentsMainActivity.newIntent(getActivity(),usrname);
                    intent.putExtra(LOGIN_VALUE, usrname);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void init(View view) {
        mButtonLogin = view.findViewById(R.id.button_login);
        mButtonSignUp = view.findViewById(R.id.button_signUp);
        mUserNameEditText = view.findViewById(R.id.login_username);
        mPasswordEditText = view.findViewById(R.id.editText_loginpassword);
    }
}
