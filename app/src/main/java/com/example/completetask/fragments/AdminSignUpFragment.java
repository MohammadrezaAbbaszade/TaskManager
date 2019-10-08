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

import com.example.completetask.R;
import com.example.completetask.activities.AdminActivity;
import com.example.completetask.activities.LoginActivity;
import com.example.completetask.model.Admin;
import com.example.completetask.model.AdminRepository;
import com.example.completetask.model.User;
import com.example.completetask.model.UserRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminSignUpFragment extends Fragment {
    private static final String VALUE_FROM_ADMIN_LOGIN_FRAGMENT = "com.example.completetask.fragments.values_from_LoginFragment";
    private Button mButtonSignUp;
    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    private String username;
    private String password;
    private String data[];
    boolean answer = true;


    public static AdminSignUpFragment newInstance(String[] valuesFromAdminFragment) {

        Bundle args = new Bundle();

        AdminSignUpFragment fragment = new AdminSignUpFragment();
        args.putStringArray(VALUE_FROM_ADMIN_LOGIN_FRAGMENT, valuesFromAdminFragment);
        fragment.setArguments(args);
        return fragment;
    }
    public AdminSignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_admin_sign_up, container, false);
        init(view);
        data = getArguments().getStringArray(VALUE_FROM_ADMIN_LOGIN_FRAGMENT);
        username = data[0];
        password = data[1];
        mUserNameEditText.setText(username);
        mPasswordEditText.setText(password);
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUserNameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String[] value = {username, password};
                Admin admin = new Admin();
                admin.setMUserName(username);
                admin.setMPassword(password);
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getActivity(), "Empty username or password", Toast.LENGTH_SHORT).show();
                } else if (AdminRepository.getInstance().checkUserName(username)) {
                    Toast.makeText(getActivity(), "This Admin is Exist", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        AdminRepository.getInstance().addAdmin(admin);
                        answer = false;
                    } catch (IllegalArgumentException e) {
                        e.getMessage();
                        Toast.makeText(getActivity(), "This Admin Is Exist!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (answer == false) {
                    Intent resultIntent = AdminActivity.newIntent(getActivity(), value, admin);
                    startActivity(resultIntent);
                }
            }
        });


       return view;
    }
    private void init(View view) {

        mButtonSignUp = view.findViewById(R.id.button_signUp_admin);
        mUserNameEditText = view.findViewById(R.id.login_usernamee_signup_admin);
        mPasswordEditText = view.findViewById(R.id.editText_loginpasswordd_signup_admin);
    }
}

