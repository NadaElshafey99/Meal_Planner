package com.example.mealplannerapplication.home_screen.view;

import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface HomeScreenViewInterface {

    void showData (ArrayList<Meal> meals);
    void addMeal (Meal meal);
}
