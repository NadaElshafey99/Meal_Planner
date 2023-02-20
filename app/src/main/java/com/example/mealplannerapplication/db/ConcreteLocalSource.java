package com.example.mealplannerapplication.db;

import android.content.Context;

import com.example.mealplannerapplication.FavoriteFireStore;
import com.example.mealplannerapplication.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ConcreteLocalSource implements LocalSource {

    private static ConcreteLocalSource localSource = null;
    private MealDAO dao;
    AppDataBase db;

    private ConcreteLocalSource(Context context) {
        db = AppDataBase.getInstance(context.getApplicationContext());
        dao = db.mealDAO();

    }

    public static ConcreteLocalSource getInstance(Context context) {
        if (localSource == null) {
            localSource = new ConcreteLocalSource(context);
        }
        return localSource;
    }

    @Override
    public void insertFavMeal(Meal meal) {
        new Thread(() -> dao.addMeal(meal)).start();
    }

    @Override
    public void insertPlanMeal(Meal meal) {
        dao.addMeal(meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(() -> dao.delete(meal)).start();
    }

    @Override
    public Flowable<List<Meal>> getAllFavMeals() {
        return dao.getFavMeals();
    }

    @Override
    public Flowable<List<Meal>> getAllWeeklyMeals() {
        return dao.getWeeklyMeals();
    }

    @Override
    public Flowable<Meal> getFavMeal(String id) {
        return dao.getFavMeal(id);
    }

    @Override
    public Flowable<List<Meal>> getAllMeals() {
        return dao.getAllMeals();
    }

    @Override
    public void deleteTable() {
        new Thread(() -> db.clearAllTables()).start();
    }
}
