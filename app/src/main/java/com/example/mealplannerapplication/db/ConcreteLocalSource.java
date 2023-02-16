package com.example.mealplannerapplication.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class ConcreteLocalSource implements LocalSource{

    private MealDAO dao;
    private static ConcreteLocalSource localSource = null;
    private LiveData<List<Meal>> favMeals;

    private ConcreteLocalSource(Context context){
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        dao = db.mealDAO();
        favMeals = dao.getFavMeals();
    }

    public static ConcreteLocalSource getInstance(Context context){
        if(localSource == null){
            localSource = new ConcreteLocalSource(context);
        }
        return localSource;
    }
    @Override
    public void insertMovie(Meal meal) {
        new Thread(() -> dao.addMeal(meal)).start();
    }

    @Override
    public void deleteMovie(Meal meal) {
        new Thread(() -> dao.delete(meal)).start();
    }

    @Override
    public LiveData<List<Meal>> getAllFavMeals() {
        return favMeals;
    }
}
