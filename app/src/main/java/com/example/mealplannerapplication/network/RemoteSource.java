package com.example.mealplannerapplication.network;

import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Root;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;

public interface RemoteSource {
    //instead of enqueue
    void getRandomMeal(NetworkInterface networkInterfaceRef);
    Observable <Root> getMealsByCategories(String category);
}
