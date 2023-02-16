package com.example.mealplannerapplication.network;

import com.example.mealplannerapplication.model.RetrievedList;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Root;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface CallsToServer {
    /*z@GET("random.php/")
    Observable<Root> getRandomMeal();*/
    @GET("filter.php")
    Observable<Root> getMealByCategory(@Query("c") String category);
    @GET
    Observable<RetrievedList> getDataFomApi(@Url String url);

}
