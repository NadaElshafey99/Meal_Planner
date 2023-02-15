package com.example.mealplannerapplication.home_screen.view;

import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;

public interface HomeScreenViewInterface {

    void showDailyMeals(ArrayList<Meal> meals);
    void showCategoryMeals(ArrayList<Meal> meals);
    void addMeal (Meal meal);
}
