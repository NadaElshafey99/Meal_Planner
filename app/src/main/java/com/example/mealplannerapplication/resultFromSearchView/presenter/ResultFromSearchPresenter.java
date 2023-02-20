package com.example.mealplannerapplication.resultFromSearchView.presenter;

import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.model.RepositoryInterface;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.resultFromSearchView.view.ResultFromSearchViewInterface;
import com.example.mealplannerapplication.searchByCategories.view.SearchByCategoriesScreenInterface;

import java.util.ArrayList;

public class ResultFromSearchPresenter implements ResultFromSearchPresenterInterface, NetworkInterface {
    private ResultFromSearchViewInterface resultFromSearchViewInterface;
    private RepositoryInterface repositoryInterface;

    public ResultFromSearchPresenter(ResultFromSearchViewInterface resultFromSearchViewInterface, RepositoryInterface repositoryInterface)
    {
        this.resultFromSearchViewInterface=resultFromSearchViewInterface;
        this.repositoryInterface=repositoryInterface;

    };


    @Override
    public void onSuccess(ArrayList<Meal> list) {
        resultFromSearchViewInterface.showMealsResult(list);
    }

    @Override
    public void onSuccessCategory(ArrayList<Meal> meals) {

    }

    @Override
    public void onFailure(String errMsg) {
        resultFromSearchViewInterface.failedToShowResultsMeal(errMsg);
    }

    @Override
    public void getResultMeals(String Url) {
        repositoryInterface.getUrl(Url);
        repositoryInterface.getData(this);
    }

    @Override
    public void onFavClicked() {

    }
}
