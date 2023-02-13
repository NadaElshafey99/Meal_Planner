package com.example.mealplannerapplication.login_screen.presenter;

import android.content.SharedPreferences;

import com.example.mealplannerapplication.model.User;

public interface LoginPresenterInterface {
    public void checkUser(User user);
    public void userToStillLogin(SharedPreferences sharedPreferences);
}
