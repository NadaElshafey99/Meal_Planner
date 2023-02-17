package com.example.mealplannerapplication.fav_screen.view.presenter;

import com.example.mealplannerapplication.fav_screen.view.FavScreenViewInterface;
import com.example.mealplannerapplication.home_screen.view.HomeScreenViewInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.RepositoryInterface;

public class FavScreenPresenter implements FavScreenPresenterInterface{

    FavScreenViewInterface viewInterfaceRef;
    RepositoryInterface repoRef;

    public FavScreenPresenter(FavScreenViewInterface view, RepositoryInterface repoRef) {
        this.viewInterfaceRef = view;
        this.repoRef = repoRef;
    }
    @Override
    public void getFavMeals() {


    }

    @Override
    public void addToFav(Meal meal) {

    }

    @Override
    public void handleFavMeal(Meal meal) {

    }

    @Override
    public void removeFromFav(Meal meal) {

    }

    @Override
    public void handlePlanMeal(Meal meal) {

    }
}
