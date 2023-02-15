package com.example.mealplannerapplication.home_screen.view.presenter;

import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;

public interface HomeScreenPresenterInterface {
 void getDailyMeals();
 void getChickenCategory();
 void addToFav(Meal meal);
}
