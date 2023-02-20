package com.example.mealplannerapplication.home_screen.presenter;

import com.example.mealplannerapplication.model.Meal;

public interface HomeScreenPresenterInterface {
    void getDailyMeals();

    void getBeefCategory();

    void getBreakfastCategory();

    void getChickenCategory();

    void getDesertCategory();

    void addToFav(Meal meal);

    void handleFavMeal(Meal meal);

    void removeFromFav(Meal meal);

    void handlePlanMeal(Meal meal);

    void clearUserData();

}
