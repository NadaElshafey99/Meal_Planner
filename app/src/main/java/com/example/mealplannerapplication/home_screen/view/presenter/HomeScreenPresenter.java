package com.example.mealplannerapplication.home_screen.view.presenter;

import com.example.mealplannerapplication.home_screen.view.HomeScreen;
import com.example.mealplannerapplication.home_screen.view.HomeScreenViewInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.network.RemoteSource;

import java.util.ArrayList;

public class HomeScreenPresenter implements HomeScreenPresenterInterface, NetworkInterface {

    HomeScreenViewInterface viewInterfaceRef;
    ArrayList<Meal> mealArrayList;
    RemoteSource remoteSource;


    public HomeScreenPresenter(HomeScreenViewInterface view, RemoteSource remoteSource) {
        this.viewInterfaceRef = view;
        this.remoteSource = remoteSource;
    }

    @Override
    public void getDailyMeals() {
        for(int i=0; i<5; i++) {
            remoteSource.getRandomMeal(this);
        }

    }

    @Override
    public void addToFav(Meal meal) {

    }

    @Override
    public void onSuccess(ArrayList<Meal> meals) {
        viewInterfaceRef.showData(mealArrayList);
    }

    @Override
    public void onFailure(String errMsg) {

    }
}
