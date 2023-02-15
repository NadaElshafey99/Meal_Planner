package com.example.mealplannerapplication.home_screen.view.presenter;

import android.util.Log;

import com.example.mealplannerapplication.home_screen.view.HomeScreen;
import com.example.mealplannerapplication.home_screen.view.HomeScreenViewInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.RepositoryInterface;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.network.RemoteSource;

import java.util.ArrayList;

public class HomeScreenPresenter implements HomeScreenPresenterInterface, NetworkInterface {

    HomeScreenViewInterface viewInterfaceRef;
    ArrayList<Meal> mealArrayList = new ArrayList<>();
    RepositoryInterface repoRef;


    public HomeScreenPresenter(HomeScreenViewInterface view, RepositoryInterface repoRef) {
        this.viewInterfaceRef = view;
        this.repoRef = repoRef;
    }

    @Override
    public void getDailyMeals() {
        for(int i=0; i<5; i++) {
            repoRef.getDailyMeal(this);
        }

    }

    @Override
    public void addToFav(Meal meal) {

    }

    @Override
    public void onSuccess(ArrayList<Meal> meals) {
        Log.i("TAG", "onSuccess: " + meals.get(0).getStrMeal());
        mealArrayList.add(meals.get(0));
        viewInterfaceRef.showData(mealArrayList);
    }

    @Override
    public void onFailure(String errMsg) {

    }
}
