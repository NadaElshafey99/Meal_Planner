package com.example.mealplannerapplication.db;

import androidx.lifecycle.LiveData;

import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface LocalSource {

    void insertMovie(Meal meal);
    void deleteMovie(Meal meal);
    LiveData<List<Meal>> getAllFavMeals();
}
