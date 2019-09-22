package com.example.completetask.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.completetask.activities.LoginActivity;
import com.example.completetask.R;
import com.example.completetask.model.User;
import com.example.completetask.model.UserRepository;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    public static final String USER_PASS_FIELD = "UserPassField";
    private static final String VALUE_FROM_LOGIN_FRAGMENT = "values_from_LoginFragment";
    private Button mButtonSignUp;
    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    private String username;
    private String password;
    private String data[];
    boolean answer = true;

    public static SignUpFragment newInstance(String[] valuesFromLoginFragment) {

        Bundle args = new Bundle();
        args.putStringArray(VALUE_FROM_LOGIN_FRAGMENT, valuesFromLoginFragment);
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        init(view);
        data = getArguments().getStringArray(VALUE_FROM_LOGIN_FRAGMENT);
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
                User user = new User();
                user.setmUserName(username);
                user.setmPassword(password);
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getActivity(), "Empty username or password", Toast.LENGTH_SHORT).show();
                } else if (UserRepository.getInstance(getContext()).checkUserName(username)) {
                    Toast.makeText(getActivity(), "This UserName is Exist", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        UserRepository.getInstance(getContext()).addUser(user);
                        answer = false;
                    } catch (IllegalArgumentException e) {
                        e.getMessage();
                        Toast.makeText(getActivity(), "This User Is Exist!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (answer == false) {
                    Intent resultIntent = LoginActivity.newIntent(getActivity(), value, user);
                    startActivity(resultIntent);
                }
            }
        });
        return view;

    }

    private void init(View view) {

        mButtonSignUp = view.findViewById(R.id.button_signUp);
        mUserNameEditText = view.findViewById(R.id.login_usernamee);
        mPasswordEditText = view.findViewById(R.id.editText_loginpasswordd);
    }
}
