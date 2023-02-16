package com.example.mealplannerapplication.searchByCategories.view;

import com.example.mealplannerapplication.model.Categories;
import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface SearchByCategoriesScreenInterface {
    void showCategories(ArrayList<Meal>categories);
    void failedToShowCategories(String errMsg);
    String sendUrl();

}
