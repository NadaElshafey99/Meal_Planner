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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    ArrayList<Meal> mealsList;
    CallsToServer callsToServer;
    private static final String RANDOM_MEAL_URL = "https://www.themealdb.com/api/json/v1/1/random.php/";
    RecyclerView recyclerView;
    DailyAdapter adapter;
    Context context;
    View view;

    public RetrofitClient(RecyclerView rc, Context c) {
        this.context = c;
        this.recyclerView = rc;
    }
    public RetrofitClient(View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void startCall(NetworkInterface interfaceRef) {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RANDOM_MEAL_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        callsToServer = retrofit.create(CallsToServer.class);

        Call<Root>  meals = callsToServer.getRandomMeal();

        Callback<Root> mMeals = new Callback<Root>() {
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
        };
        meals.enqueue(mMeals);
    }



    void startCall(int id) {
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RANDOM_MEAL_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
