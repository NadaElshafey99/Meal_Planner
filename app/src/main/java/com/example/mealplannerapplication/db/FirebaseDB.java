package com.example.mealplannerapplication.db;

import android.content.Context;

import com.example.mealplannerapplication.model.Meal;
import com.google.firebase.auth.FirebaseUser;

public interface FirebaseDB {
    void insertPlanMeal(Meal meal);
    void getWeeklyMeals();
}
