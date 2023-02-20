package com.example.mealplannerapplication.login_screen.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mealplannerapplication.NavigationActivity;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.db.ConcreteFirebaseDB;
import com.example.mealplannerapplication.login_screen.view.LoginScreen;
import com.example.mealplannerapplication.login_screen.view.LoginScreenInterface;
import com.example.mealplannerapplication.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;


public class LoginPresenter implements LoginPresenterInterface {

    public static final String SHRED_PREFERENCE_FILE = "LoginFile";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    LoginScreenInterface loginScreenInterface;
    User user;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private SharedPreferences sharedPreferences;
    private ConcreteFirebaseDB concreteFirebaseDB;

    public LoginPresenter(LoginScreenInterface loginScreenInterface, ConcreteFirebaseDB concreteFirebaseDB) {
        this.loginScreenInterface = loginScreenInterface;
        this.concreteFirebaseDB = concreteFirebaseDB;
        user = new User();
    }


    @Override
    public void checkUser(User user) {
        this.user = user;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(user.getUserEmail(), user.getUserPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Get instance of current user
                            firebaseUser = firebaseAuth.getCurrentUser();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_EMAIL, user.getUserEmail());
                            editor.putString(KEY_PASSWORD, user.getUserPassword());
                            editor.commit();
                            loginScreenInterface.onSuccessCheckUser();


                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                loginScreenInterface.onFailureAuthInvalidUser();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                loginScreenInterface.onFailureAuthInvalidCredentials();
                            } catch (Exception e) {
                                loginScreenInterface.onFailureCheckUser();
                            }
                        }

                    }
                });
    }

    @Override
    public void userToStillLogin(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        String userName = sharedPreferences.getString(KEY_EMAIL, null);
        String password = sharedPreferences.getString(KEY_PASSWORD, null);
        if (userName != null && password != null) {
            loginScreenInterface.alreadyLogin();
        }
    }

    @Override
    public void getDataWeeklyMealsFromFirebase() {
        concreteFirebaseDB.getWeeklyMeals();
    }
}
