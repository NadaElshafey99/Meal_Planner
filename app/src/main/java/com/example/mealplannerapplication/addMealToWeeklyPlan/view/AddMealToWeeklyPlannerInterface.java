package com.example.mealplannerapplication.addMealToWeeklyPlan.view;

import com.example.mealplannerapplication.model.Meal;

public interface AddMealToWeeklyPlannerInterface {
    void getMealDetailsToInsert(Meal meal);

    void failedToGetMeal(String errMsg);

    String sendUrl();

    void insertMealToDB(Meal meal);

    void successToInsertMeal();

    void insertMealToFirebase(Meal meal);

}
