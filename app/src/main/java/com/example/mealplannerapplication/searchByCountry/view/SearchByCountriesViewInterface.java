package com.example.mealplannerapplication.searchByCountry.view;

import java.util.ArrayList;

public interface SearchByCountriesViewInterface {
    void showCountries(ArrayList<?> countries);
    void failedToShowCategories(String errMsg);
    String sendUrl();
}
