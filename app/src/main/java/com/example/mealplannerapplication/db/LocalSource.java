package com.example.mealplannerapplication.db;

import com.example.mealplannerapplication.model.Meal;

import io.reactivex.rxjava3.core.Flowable;

public interface LocalSource {

    void insertFavMeal(Meal meal);
    void insertPlanMeal(Meal meal);
    void deleteMeal(Meal meal);
    Flowable<Meal> getAllFavMeals();
    Flowable<Meal> getFavMeal(String id);
}
