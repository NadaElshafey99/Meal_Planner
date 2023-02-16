package com.example.mealplannerapplication.model;

import com.example.mealplannerapplication.network.NetworkInterface;
import io.reactivex.rxjava3.core.Observable;

public interface RepositoryInterface {
    void getDailyMeal(NetworkInterface networkInterfaceRef);
    Observable <Root> GetMealByCategory(String category);

}
