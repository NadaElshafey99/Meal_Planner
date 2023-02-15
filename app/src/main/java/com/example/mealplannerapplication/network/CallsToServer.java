package com.example.mealplannerapplication.network;

import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Root;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallsToServer {
    @GET("random.php/")
    Observable<Root> getRandomMeal();


}
