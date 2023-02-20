package com.example.mealplannerapplication.signup_screen.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mealplannerapplication.MainActivity;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.User;
import com.example.mealplannerapplication.signup_screen.presenter.SignupPresenter;
import com.google.android.material.textfield.TextInputLayout;


public class SignupScreen extends Fragment implements SignupScreenInterface {

    private TextInputLayout name;
    private TextInputLayout email;
    private TextInputLayout password;
    private TextInputLayout confirmPassword;
    private Button signupButton;
    private Editable userName;
    private Editable userEmail;
    private Editable userPassword;
    private Editable userConfirmPassword;
    private SignupPresenter signupPresenter;
    private User user;


    public SignupScreen() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_screen, container, false);
        name = view.findViewById(R.id.signup_nameField);
        email = view.findViewById(R.id.signup_emailField);
        password = view.findViewById(R.id.signup_passwordField);
        confirmPassword = view.findViewById(R.id.signup_confirmPass);
        signupButton = view.findViewById(R.id.signup_btn);
        signupPresenter = new SignupPresenter(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = name.getEditText().getText();
                userEmail = email.getEditText().getText();
                userPassword = password.getEditText().getText();
                userConfirmPassword = confirmPassword.getEditText().getText();
                if (TextUtils.isEmpty(userName)) {
                    name.setError(getString(R.string.pleaseEnterUserName));
                } else {
                    name.setError(null);
                }

                if (TextUtils.isEmpty(userEmail)) {
                    email.setError(getString(R.string.pleaseEnterEmail));
                } else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                        email.setError(getString(R.string.pleaseEnterValidEmail));
                    } else {
                        email.setError(null);
                    }
                }

                if (TextUtils.isEmpty(userPassword)) {
                    password.setError(getString(R.string.pleaseEnterPassword));
                } else {
                    if (userPassword.length() < 6) {
                        password.setError(getString(R.string.passwordIsWeek));
                    } else {
                        password.setError(null);
                    }
                }

                if (TextUtils.isEmpty(userConfirmPassword)) {
                    confirmPassword.setError(getString(R.string.pleaseEnterConfirmPassword));
                } else {
                    if (userConfirmPassword.toString().equals(userPassword.toString())) {
                        confirmPassword.setError(null);
                    } else {
                        confirmPassword.setError(getString(R.string.wrongConfirmPassword));
                    }
                }

                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword) && !TextUtils.isEmpty(userConfirmPassword) ) {
                    user = new User(userName.toString(), userEmail.toString(), userPassword.toString(), userConfirmPassword.toString());
                    signupPresenter.registerUser(user);
                }
            }
        });
    }

    @Override
    public void onSuccessRegistered() {
        Toast.makeText(SignupScreen.super.getActivity(), "DONE", Toast.LENGTH_SHORT).show();
        //open use's profile
        Intent myIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(myIntent);
        getActivity().finish();

    }

    @Override
    public void onFailureRegistered() {
        email.setError(getString(R.string.repeatingEmail));
        email.requestFocus();
    }

    @Override
    public void onFailureSetData() {
        Toast.makeText(SignupScreen.super.getActivity(), getString(R.string.registeredIsFailed), Toast.LENGTH_SHORT).show();
    }
}

