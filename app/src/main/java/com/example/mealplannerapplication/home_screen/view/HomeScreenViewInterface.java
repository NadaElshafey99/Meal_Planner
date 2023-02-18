package com.example.mealplannerapplication.home_screen.view;

import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;

public interface HomeScreenViewInterface {

//    DailyAdapter dailyAdapter;
//
//    DailyAdapter beefAdapter;
//    DailyAdapter breakfastAdapter;
//    DailyAdapter chickenAdapter;
//    DailyAdapter desertAdapter;
    void showDailyMeals(ArrayList<Meal> meals);
    void showBeefCategory(ArrayList<Meal> meals);
    void breakfast(ArrayList<Meal> meals);
    void showChickenCategory(ArrayList<Meal> meals);
    void showDesertCategory(ArrayList<Meal> meals);
    void handleFavBookmark(Meal meal);

    void handlePlanBtn(Meal meal);
}
