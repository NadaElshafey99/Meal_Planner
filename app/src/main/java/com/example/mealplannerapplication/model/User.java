package com.example.mealplannerapplication.model;

import androidx.annotation.Keep;

@Keep
public class User {
   private String userName;
   private String userEmail;
   private String userPassword;
   private String userConfirmPassword;

       public User()
       {

       }
    public User(String userEmail, String userPassword)
    {
        this.userEmail=userEmail;
        this.userPassword=userPassword;
    }
    public User(String userName, String userEmail, String userPassword,String userConfirmPassword)
    {
        this.userName=userName;
        this.userEmail=userEmail;
        this.userPassword=userPassword;
        this.userConfirmPassword=userConfirmPassword;
    }

    public String getUserConfirmPassword() {
        return userConfirmPassword;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
        this.userConfirmPassword = userConfirmPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
