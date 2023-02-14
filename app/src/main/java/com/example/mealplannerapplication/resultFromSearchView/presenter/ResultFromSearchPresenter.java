package com.example.mealplannerapplication.resultFromSearchView.presenter;

import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.model.RepositoryInterface;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.searchByCategories.view.SearchByCategoriesScreenInterface;

import java.util.ArrayList;

public class ResultFromSearchPresenter implements ResultFromSearchPresenterInterface, NetworkInterface {
    SearchByCategoriesScreenInterface searchByCategoriesScreenInterface;
    private RepositoryInterface repositoryInterface;

    public ResultFromSearchPresenter(SearchByCategoriesScreenInterface searchByCategoriesScreenInterface, RepositoryInterface repositoryInterface)
    {
        this.searchByCategoriesScreenInterface=searchByCategoriesScreenInterface;
        this.repositoryInterface=repositoryInterface;

    };

    @Override
    public void onSuccess(ArrayList<?> list) {
        searchByCategoriesScreenInterface.showCategories(list);
    }

    @Override
    public void onFailure(String errMsg) {
        searchByCategoriesScreenInterface.failedToShowCategories(errMsg);
    }

    @Override
    public void getCategoriesSelected(String Url) {
        repositoryInterface.getUrl(Url);
        repositoryInterface.getData(this);
    }
}
