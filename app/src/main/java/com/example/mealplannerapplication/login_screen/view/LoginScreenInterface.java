package com.example.mealplannerapplication.login_screen.view;

import android.content.Context;

public interface LoginScreenInterface {

    void onSuccessCheckUser();
    void onFailureCheckUser();
    void alreadyLogin();
    void onFailureAuthInvalidUser();
    void onFailureAuthInvalidCredentials();
}
