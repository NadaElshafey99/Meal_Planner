package com.example.mealplannerapplication.searchByIngredients.view;

import java.util.ArrayList;

public interface SearchByIngredientsViewInterface {
    void showCategories(ArrayList<?> ingredients);
    void failedToShowCategories(String errMsg);
    String sendUrl();
}
