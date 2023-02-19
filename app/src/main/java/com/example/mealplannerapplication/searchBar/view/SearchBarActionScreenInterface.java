package com.example.mealplannerapplication.searchBar.view;

import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;

public interface SearchBarActionScreenInterface {
    void showResultOfSearch(ArrayList<Meal> result);
    void failedToShowResult(String errMsg);
    String sendUrl();
}
