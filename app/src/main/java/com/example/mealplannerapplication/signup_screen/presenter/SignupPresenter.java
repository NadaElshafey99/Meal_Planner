package com.example.mealplannerapplication.signup_screen.presenter;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mealplannerapplication.MainActivity;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.UserDetails;
import com.example.mealplannerapplication.signup_screen.view.SignupScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupPresenter {

    private SignupPresenterInterface signupPresenterInterface;
    public SignupPresenter(SignupPresenterInterface signupPresenterInterface)
    {
        this.signupPresenterInterface=signupPresenterInterface;
    }


}
