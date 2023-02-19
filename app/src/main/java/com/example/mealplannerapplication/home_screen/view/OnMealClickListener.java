package com.example.mealplannerapplication.home_screen.view;

import com.example.mealplannerapplication.model.Meal;

public interface OnMealClickListener {

    void onFavClicked(Meal meal);
    void onPlanClicked(Meal meal);
}
