package com.example.mealplannerapplication.model;

import com.example.mealplannerapplication.network.NetworkInterface;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface RepositoryInterface {
    void getData(NetworkInterface networkInterfaceRef);
    Observable <Root> getMealByCategory(String category);
    void getUrl(String url);

    Flowable<List<Meal>> getFavMeals();
    Flowable<Meal> getFavMeal(String id);
    void addMealToFav(Meal meal);

    void removeMealFromFav(Meal meal);

}
