package com.example.mealplannerapplication.searchBar.presenter;

import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.searchBar.view.SearchBarActionScreenInterface;

import java.util.ArrayList;

public class SearchBarPresenter implements SearchBarPresenterInterface, NetworkInterface {

    private SearchBarActionScreenInterface searchBarActionScreenInterface;
    private Repository repository;

    public SearchBarPresenter(SearchBarActionScreenInterface searchBarActionScreenInterface,Repository repository)
    {
        this.searchBarActionScreenInterface=searchBarActionScreenInterface;
        this.repository=repository;
    }
    @Override
    public void getMealsFiltered(String Url) {
        repository.getUrl(Url);
        repository.getData(this);
    }

    @Override
    public void onSuccess(ArrayList<Meal> mealsList) {
        searchBarActionScreenInterface.showResultOfSearch(mealsList);
    }
    @Override
    public void onFailure(String errMsg) {
        searchBarActionScreenInterface.failedToShowResult(errMsg);
    }

    @Override
    public void onSuccessCategory(ArrayList<Meal> meals) {

    }
}
