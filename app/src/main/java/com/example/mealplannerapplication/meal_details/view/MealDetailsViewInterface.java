package com.example.mealplannerapplication.meal_details.view;

import com.example.mealplannerapplication.model.Meal;
import java.util.ArrayList;

public interface MealDetailsViewInterface {
    void showMealDetails(Meal meal);
    void failedToShowMealDetails(String errMsg);
    String sendUrl();
}
