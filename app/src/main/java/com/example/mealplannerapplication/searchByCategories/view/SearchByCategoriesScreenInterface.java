package com.example.mealplannerapplication.searchByCategories.view;

import com.example.mealplannerapplication.model.Categories;

import java.util.ArrayList;
import java.util.List;

public interface SearchByCategoriesScreenInterface {
    void showCategories(ArrayList<?>categories);
    void failedToShowCategories(String errMsg);
    String sendUrl();

}
