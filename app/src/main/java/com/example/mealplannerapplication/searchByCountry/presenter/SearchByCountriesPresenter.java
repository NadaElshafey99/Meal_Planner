package com.example.mealplannerapplication.searchByCountry.presenter;

import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.model.RepositoryInterface;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.searchByCountry.view.SearchByCountriesViewInterface;

import java.util.ArrayList;

public class SearchByCountriesPresenter implements SearchByCountriesPresenterInterface, NetworkInterface {

    private SearchByCountriesViewInterface searchByCountriesViewInterface;
    private RepositoryInterface repositoryInterface;
    public SearchByCountriesPresenter(SearchByCountriesViewInterface searchByCountriesViewInterface, RepositoryInterface repositoryInterface)
    {
        this.searchByCountriesViewInterface=searchByCountriesViewInterface;
        this.repositoryInterface=repositoryInterface;
    }

    @Override
    public void getCountries(String url) {
        repositoryInterface.getUrl(url);
        repositoryInterface.getData(this);
    }

    @Override
    public void onSuccess(ArrayList<Meal> list) {
        searchByCountriesViewInterface.showCountries(list);
    }

    @Override
    public void onSuccessCategory(ArrayList<Meal> meals) {

    }

    @Override
    public void onFailure(String errMsg) {
        searchByCountriesViewInterface.failedToShowCategories(errMsg);
    }
}
