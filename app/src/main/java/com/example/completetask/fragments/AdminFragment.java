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
import android.widget.TextView;
import android.widget.Toast;

import com.example.completetask.R;
import com.example.completetask.activities.AdminPagerActivity;
import com.example.completetask.activities.AdminSignUpActivity;
import com.example.completetask.activities.FragmentsMainActivity;
import com.example.completetask.activities.SignUpActivity;
import com.example.completetask.model.AdminRepository;
import com.example.completetask.model.UserRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminFragment extends Fragment {
    private static final String VALUE_FROM_ADMIN_SIGN_UP_FRAGMENT = "com.example.completetask.fragments.value_from_sign_up_fragment";
    private static final String USER_FROM_ADMIN_SIGN_UP_FRAGMENT = "com.example.completetask.fragments.idFromSignUpFragment";
    private Button mLogin;
    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    private TextView mButtonSignUpTextVview;
    String[] valueFromSignUpFragment;
    private String username;
    public static AdminFragment newInstance(String[] valueFromAdminSignUpFragment, String usernameFromSignUpFragment) {

        Bundle args = new Bundle();
        args.putStringArray(VALUE_FROM_ADMIN_SIGN_UP_FRAGMENT, valueFromAdminSignUpFragment);
        args.putString(USER_FROM_ADMIN_SIGN_UP_FRAGMENT, usernameFromSignUpFragment);
        AdminFragment fragment = new AdminFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AdminFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        valueFromSignUpFragment = getArguments().getStringArray(VALUE_FROM_ADMIN_SIGN_UP_FRAGMENT);
        username = getArguments().getString(USER_FROM_ADMIN_SIGN_UP_FRAGMENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        init(view);
        if (valueFromSignUpFragment != null) {
            mUserNameEditText.setText(valueFromSignUpFragment[0]);
            mPasswordEditText.setText(valueFromSignUpFragment[1]);
        }

        mButtonSignUpTextVview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrname = mUserNameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String[] values = {usrname, password};
                Intent intent = AdminSignUpActivity.newIntent(getActivity(), values);
                startActivity(intent);
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrname = mUserNameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                if (AdminRepository.getInstance(getContext()).login(usrname, password)) {
                    Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    Intent intent = AdminPagerActivity.newIntent(getActivity(), usrname);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
    });

       return view;
}

    private void init(View view) {
        mUserNameEditText = view.findViewById(R.id.login_username_admin);
        mPasswordEditText = view.findViewById(R.id.editText_admin_password);
        mButtonSignUpTextVview=view.findViewById(R.id.button_signUp_admin);
        mLogin = view.findViewById(R.id.button_login_admin);
    }

}
