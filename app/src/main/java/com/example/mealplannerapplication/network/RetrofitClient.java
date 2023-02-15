package com.example.mealplannerapplication.network;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Root;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements RemoteSource {
    ArrayList<Meal> mealsList;
    CallsToServer callsToServer;
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    RecyclerView recyclerView;

    private static RetrofitClient client = null;

    public static synchronized RetrofitClient getInstance() {
        if (client == null) {
            client = new RetrofitClient();
        }
        return client;
    }

    private RetrofitClient() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        callsToServer = retrofit.create(CallsToServer.class);
    }


    @Override
    public void getRandomMeal(NetworkInterface interfaceRef) {
        Observable<Root> meals = callsToServer.getRandomMeal();
        Disposable d = meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> interfaceRef.onSucessDaily(i.getMeals()), throwable -> interfaceRef.onFailure(throwable.getMessage()));
    }

    @Override
    public Observable <Root> getMealsByCategories(String category) {
        Observable<Root> meals = callsToServer.getMealByCategory(category);
        return meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}