package com.example.mealplannerapplication.home_screen.view.presenter;

import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;

public interface HomeScreenPresenterInterface {
 ArrayList<Meal> getDailyMeals();
 void addToFav(Meal meal);
}
