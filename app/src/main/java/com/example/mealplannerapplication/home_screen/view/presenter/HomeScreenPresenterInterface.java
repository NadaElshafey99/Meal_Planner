package com.example.mealplannerapplication.home_screen.view.presenter;

import com.example.mealplannerapplication.model.Meal;

public interface HomeScreenPresenterInterface {
    void getDailyMeals();

    void getBeefCategory();

    void getBreakfastCategory();

    void getChickenCategory();

    void getDesertCategory();

    void addToFav(Meal meal);
}
