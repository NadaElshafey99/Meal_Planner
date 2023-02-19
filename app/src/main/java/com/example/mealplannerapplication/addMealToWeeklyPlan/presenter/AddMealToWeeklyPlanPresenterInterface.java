package com.example.mealplannerapplication.addMealToWeeklyPlan.presenter;

import com.example.mealplannerapplication.addMealToWeeklyPlan.view.AddMealToWeeklyPlannerInterface;
import com.example.mealplannerapplication.model.Meal;

public interface AddMealToWeeklyPlanPresenterInterface {
    void successToInsertMeal();
    void getMealDetails(String Url);
    void insertMealToDB(Meal meal);
    void insertMealToFirebaseDB(Meal meal);
    void failedToInsertMeal(String errMsg);
}
