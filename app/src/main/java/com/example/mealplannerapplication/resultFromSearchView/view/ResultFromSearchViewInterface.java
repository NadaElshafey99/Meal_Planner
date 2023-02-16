package com.example.mealplannerapplication.resultFromSearchView.view;

import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;

public interface ResultFromSearchViewInterface {
    void showMealsResult(ArrayList<Meal> resultMeals);
    void failedToShowResultsMeal(String errMsg);
    String sendUrl();
}
