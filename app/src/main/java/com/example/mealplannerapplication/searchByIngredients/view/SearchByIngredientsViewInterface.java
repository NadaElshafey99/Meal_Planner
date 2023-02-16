package com.example.mealplannerapplication.searchByIngredients.view;

import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;

public interface SearchByIngredientsViewInterface {
    void showCategories(ArrayList<Meal> ingredients);
    void failedToShowCategories(String errMsg);
    String sendUrl();
}
