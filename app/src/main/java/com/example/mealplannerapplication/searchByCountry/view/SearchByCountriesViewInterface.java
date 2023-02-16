package com.example.mealplannerapplication.searchByCountry.view;

import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;

public interface SearchByCountriesViewInterface {
    void showCountries(ArrayList<Meal> countries);
    void failedToShowCategories(String errMsg);
    String sendUrl();
}
