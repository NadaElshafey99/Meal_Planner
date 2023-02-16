package com.example.mealplannerapplication.home_screen.view.presenter;

import android.util.Log;

import com.example.mealplannerapplication.home_screen.view.HomeScreenViewInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.RepositoryInterface;
import com.example.mealplannerapplication.network.NetworkInterface;

import java.util.ArrayList;

import io.reactivex.rxjava3.disposables.Disposable;

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


    public void getBeefCategory() {
        Disposable d = repoRef.GetMealByCategory("Beef").subscribe(i -> viewInterfaceRef.showBeefCategory(i.getMeals()));
    }

    @Override
    public void getBreakfastCategory() {
        Disposable d = repoRef.GetMealByCategory("Breakfast").subscribe(i -> viewInterfaceRef.breakfast(i.getMeals()));
    }

    @Override
    public void getChickenCategory() {
       Disposable d = repoRef.GetMealByCategory("Chicken").subscribe(i -> viewInterfaceRef.showChickenCategory(i.getMeals()));
    }

    @Override
    public void getDesertCategory() {
        Disposable d = repoRef.GetMealByCategory("Dessert").subscribe(i -> viewInterfaceRef.showDesertCategory(i.getMeals()));
    }


    @Override
    public void addToFav(Meal meal) {

    }

    @Override
    public void onSucessDaily(ArrayList<Meal> meals) {
            mealArrayList.add(meals.get(0));
            viewInterfaceRef.showDailyMeals(mealArrayList);

    }

    @Override
    public void onSuccessCategory(ArrayList<Meal> meals) {
        viewInterfaceRef.showChickenCategory(meals);
    }

    @Override
    public void onFailure(String errMsg) {
        Log.i("TAG", "onFailure: ");
    }
}
