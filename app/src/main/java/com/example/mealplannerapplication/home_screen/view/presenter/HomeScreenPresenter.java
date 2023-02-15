package com.example.mealplannerapplication.home_screen.view.presenter;

import com.example.mealplannerapplication.home_screen.view.HomeScreen;
import com.example.mealplannerapplication.home_screen.view.HomeScreenViewInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.network.NetworkInterface;

import java.util.ArrayList;

public class HomeScreenPresenter implements HomeScreenPresenterInterface, NetworkInterface {

    HomeScreenViewInterface viewInterfaceRef;
    ArrayList<Meal> mealArrayList;


    public HomeScreenPresenter(HomeScreenViewInterface view) {
        this.viewInterfaceRef = view;
    }

    @Override
    public ArrayList<Meal> getDailyMeals() {
        return mealArrayList;
    }

    @Override
    public void addToFav(Meal meal) {

    }

    @Override
    public void onSuccess(ArrayList<Meal> meals) {
        mealArrayList.add(meals.get(0));
    }

    @Override
    public void onFailure(String errMsg) {

    }
}
