package com.example.mealplannerapplication.meal_details.presenter;

import com.example.mealplannerapplication.meal_details.view.MealDetailsViewInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.RepositoryInterface;
import com.example.mealplannerapplication.network.NetworkInterface;
import java.util.ArrayList;

public class MealDetailsPresenter implements MealDetailsPresenterInterface, NetworkInterface {

    private MealDetailsViewInterface mealDetailsViewInterface;
    private RepositoryInterface repositoryInterface;

    public MealDetailsPresenter(MealDetailsViewInterface mealDetailsViewInterface, RepositoryInterface repositoryInterface)
    {
        this.mealDetailsViewInterface=mealDetailsViewInterface;
        this.repositoryInterface=repositoryInterface;
    }

    @Override
    public void getMealDetails(String Url) {
        repositoryInterface.getUrl(Url);
        repositoryInterface.getData(this);
    }

    @Override
    public void onFailure(String errMsg) {
        mealDetailsViewInterface.failedToShowMealDetails(errMsg);
    }
    @Override
    public void onSuccess(ArrayList<Meal> mealsList) {
        mealDetailsViewInterface.showMealDetails(mealsList.get(0));
    }
    @Override
    public void onSuccessCategory(ArrayList<Meal> meals) {

    }
}
