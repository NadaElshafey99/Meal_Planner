package com.example.mealplannerapplication.network;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import com.example.mealplannerapplication.home_screen.view.DailyAdapter;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Root;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements RemoteSource{
    ArrayList<Meal> mealsList;
    CallsToServer callsToServer;
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    RecyclerView recyclerView;
    View view;
     private static RetrofitClient client = null;

    public static RetrofitClient getInstance(RecyclerView rc){
        if(client == null){
            client = new RetrofitClient(rc);
        }
        return client;
    }

    private RetrofitClient(RecyclerView rc) {
        this.recyclerView = rc;
    }
    private RetrofitClient(View view) {
        this.view = view;
    }

    @Override
    public void getRandomMeal(NetworkInterface interfaceRef) {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        callsToServer = retrofit.create(CallsToServer.class);

        Call<Root> meals = callsToServer.getRandomMeal();
        meals.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mealsList = response.body().getMeals();
                    interfaceRef.onSuccess(mealsList);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }




    void getRandomMeal(int id) {
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


}
