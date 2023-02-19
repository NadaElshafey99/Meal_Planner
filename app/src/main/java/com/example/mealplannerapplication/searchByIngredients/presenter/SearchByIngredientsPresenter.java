package com.example.mealplannerapplication.searchByIngredients.presenter;

import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.model.RepositoryInterface;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.searchByIngredients.view.SearchByIngredientsViewInterface;

import java.util.ArrayList;

public class SearchByIngredientsPresenter implements SearchByIngredientsPresenterInterface,NetworkInterface{
    private SearchByIngredientsViewInterface searchByIngredientsViewInterface;
    Repository repository;

    public SearchByIngredientsPresenter(SearchByIngredientsViewInterface searchByIngredientsViewInterface, Repository repository){
        this.searchByIngredientsViewInterface=searchByIngredientsViewInterface;
        this.repository=repository;
    }


    @Override
    public void getIngredients(String url) {
        repository.getUrl(url);
        repository.getData(this);

    }

    @Override
    public void onSuccess(ArrayList<Meal> list) {
        searchByIngredientsViewInterface.showIngredients(list);
    }

    @Override
    public void onSuccessCategory(ArrayList<Meal> meals) {

    }

    @Override
    public void onFailure(String errMsg) {
        searchByIngredientsViewInterface.failedToShowIngredients(errMsg);
    }
}
