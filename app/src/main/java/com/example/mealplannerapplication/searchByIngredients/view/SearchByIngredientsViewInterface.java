package com.example.mealplannerapplication.searchByIngredients.view;

import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;

public interface SearchByIngredientsViewInterface {
    void showIngredients(ArrayList<Meal> ingredients);
    void failedToShowIngredients(String errMsg);
    String sendUrl();
}
