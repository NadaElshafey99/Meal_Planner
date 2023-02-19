package com.example.mealplannerapplication.meal_plan.presenter;

import com.example.mealplannerapplication.fav_screen.view.FavScreenViewInterface;
import com.example.mealplannerapplication.meal_plan.view.MealPlanScreenInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.RepositoryInterface;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealPlanPresenter implements MealPlanPresenterInterface{

    private MealPlanScreenInterface mealPlanScreenInterface;
    private RepositoryInterface repositoryInterface;
    public MealPlanPresenter(MealPlanScreenInterface mealPlanScreenInterface, RepositoryInterface repositoryInterface) {
        this.mealPlanScreenInterface = mealPlanScreenInterface;
        this.repositoryInterface = repositoryInterface;
    }
    @Override
    public void getWeeklyMeals() {
        repositoryInterface.getWeeklyMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> mealPlanScreenInterface.showWeeklyMeals(meals));
    }
}
