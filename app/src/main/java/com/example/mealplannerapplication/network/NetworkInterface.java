package com.example.mealplannerapplication.network;


import java.util.ArrayList;

public interface NetworkInterface {
     void onSucessDaily(ArrayList<Meal> meals);
     void onSuccessCategory(ArrayList<Meal> meals);
      void onFailure(String errMsg);
}
