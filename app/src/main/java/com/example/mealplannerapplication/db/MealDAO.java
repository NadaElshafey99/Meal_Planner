package com.example.mealplannerapplication.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Upsert;

import com.example.mealplannerapplication.model.Meal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface MealDAO {
    @Query("SELECT * From meals")
    LiveData<List<Meal>> getFavMeals();

    @Upsert
    void addMeal(Meal meal);

    @Delete
    void delete(Meal meal);
}
