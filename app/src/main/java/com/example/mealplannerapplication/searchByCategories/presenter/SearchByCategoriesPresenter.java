package com.example.mealplannerapplication.searchByCategories.presenter;

import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.RepositoryInterface;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.searchByCategories.view.SearchByCategoriesScreenInterface;

import java.util.ArrayList;

public class SearchByCategoriesPresenter implements SearchByCategoriesPresenterInterface, NetworkInterface {

    private SearchByCategoriesScreenInterface searchByCategoriesScreenInterface;
    private RepositoryInterface repositoryInterface;
    public SearchByCategoriesPresenter(SearchByCategoriesScreenInterface searchByCategoriesScreenInterface, RepositoryInterface repositoryInterface)
    {
        this.searchByCategoriesScreenInterface=searchByCategoriesScreenInterface;
        this.repositoryInterface=repositoryInterface;
    }

    @Override
    public void onSuccess(ArrayList<Meal> categories) {
        searchByCategoriesScreenInterface.showCategories(categories);
    }

    @Override
    public void onSuccessCategory(ArrayList<Meal> meals) {

    }

    @Override
    public void onFailure(String errMsg) {
        searchByCategoriesScreenInterface.failedToShowCategories(errMsg);
    }

    @Override
    public void getCategories(String url) {
        repositoryInterface.getUrl(url);
        repositoryInterface.getData(this);
    }

}
