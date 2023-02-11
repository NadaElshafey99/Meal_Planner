package com.example.mealplannerapplication.signup_screen.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mealplannerapplication.*;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mealplannerapplication.MainActivity;
import com.example.mealplannerapplication.NavigationActivity;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.home_screen.view.HomeScreen;
import com.example.mealplannerapplication.signup_screen.presenter.SignupPresenter;
import com.example.mealplannerapplication.signup_screen.presenter.SignupPresenterInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignupScreen extends Fragment /*implements SignupPresenterInterface*/ {

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


    public SignupScreen() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_screen, container, false);
        name = view.findViewById(R.id.signup_nameField);
        email = view.findViewById(R.id.signup_emailField);
        password = view.findViewById(R.id.signup_passwordField);
        confirmPassword = view.findViewById(R.id.signup_confirmPass);
        signupButton = view.findViewById(R.id.signup_btn);
        userName = name.getEditText().getText();
        userEmail = email.getEditText().getText();
        userPassword = password.getEditText().getText();
        userConfirmPassword = confirmPassword.getEditText().getText();
        //signupPresenter=new SignupPresenter(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword) && !TextUtils.isEmpty(userConfirmPassword)) {
//                    signupPresenter.registerUser(userName.toString(), userEmail.toString(), userPassword.toString(), userConfirmPassword.toString());
                    registerUser(userName.toString(), userEmail.toString(), userPassword.toString(), userConfirmPassword.toString());

                }
            }
        });
    }
    public void registerUser(String userName, String userEmail, String userPassword, String userConfirmPassword)
    {
        FirebaseAuth authentication = FirebaseAuth.getInstance();
        authentication.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(SignupScreen.super.getActivity(),
                new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) //create user
                        {
                            FirebaseUser firebaseUser = authentication.getCurrentUser();
                            //Enter data of user
                            UserDetails userData = new UserDetails(userName, userEmail, userPassword);
                            //Extracting user reference from DB
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
                            //to refer to data later very asy with unique ID
                            databaseReference.child(firebaseUser.getUid()).setValue(userData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) // saved/set data in DB
                                            {
                                                //send verification
                                                firebaseUser.sendEmailVerification();
                                                Toast.makeText(SignupScreen.super.getActivity(), "DONE, Please verify your email", Toast.LENGTH_SHORT).show();
                                                //open use's profile
                                                Intent myIntent = new Intent(getActivity(), MainActivity.class);
                                                startActivity(myIntent);
                                                getActivity().finish();
                                            } else {
                                                Toast.makeText(SignupScreen.super.getActivity(), "Registered is failed, Please try again ", Toast.LENGTH_SHORT).show();

                                            }

                                        }
                                    });
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthUserCollisionException exception) {
                                email.setError(getString(R.string.repeatingEmail));
                                email.requestFocus();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });

    }

}

