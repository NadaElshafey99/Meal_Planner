package com.example.mealplannerapplication.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Upsert;

import com.example.mealplannerapplication.model.Meal;

import java.util.List;


import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface MealDAO {
    @Query("SELECT * From meals WHERE isFav = 1")
    Flowable<List<Meal>> getFavMeals();

    @Query("SELECT * From meals WHERE isFav = 0 AND mealDay IS NOT NULL ")
    Flowable<List<Meal>> getWeeklyMeals();

    @Query("SELECT * From meals WHERE idMeal = :id")
    Flowable<Meal> getFavMeal(String id);

    @Query("SELECT * From meals")
    Flowable<List<Meal>> getAllMeals();

    @Upsert
    void addMeal(Meal meal);

    @Delete
    void delete(Meal meal);


}
