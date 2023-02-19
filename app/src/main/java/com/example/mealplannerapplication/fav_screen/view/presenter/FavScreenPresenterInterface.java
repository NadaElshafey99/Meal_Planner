package com.example.mealplannerapplication.fav_screen.view.presenter;

import com.example.mealplannerapplication.model.Meal;

public interface FavScreenPresenterInterface {

    void getFavMeals();

    void addToFav(Meal meal);

    void handleFavMeal(Meal meal);

    void removeFromFav(Meal meal);

    void handlePlanMeal(Meal meal);
}
