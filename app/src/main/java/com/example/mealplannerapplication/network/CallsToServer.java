package com.example.mealplannerapplication.network;

import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallsToServer {
    @GET("meals")
    Call<Root> getRandomMeal();
    @GET("products/{id}")
    Call<Meal> getProduct(@Path("id") int id);
}
