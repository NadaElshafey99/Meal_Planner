package com.example.mealplannerapplication.searchByIngredients.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Ingredients;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.network.RetrofitClient;
import com.example.mealplannerapplication.searchByIngredients.presenter.SearchByIngredientsPresenter;

import java.util.ArrayList;

public class SearchByIngredientActivity extends AppCompatActivity implements SearchByIngredientsViewInterface,Communicator {

    private ChosenIngredientsFragment chosenIngredientsFragment;
    private AllIngredientsFragment allIngredientsFragment;
    private String url;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private SearchByIngredientsPresenter searchByIngredientsPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_ingredient);
        chosenIngredientsFragment=new ChosenIngredientsFragment();
        allIngredientsFragment=new AllIngredientsFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.selectedIngredientsRecyclerView,chosenIngredientsFragment);
        fragmentTransaction.add(R.id.ingredientsRecyclerView,allIngredientsFragment);
        fragmentTransaction.commit();
        searchByIngredientsPresenter=new SearchByIngredientsPresenter
                (this,
                        Repository.getInstance(RetrofitClient.getInstance(),this));
        searchByIngredientsPresenter.getIngredients(sendUrl());
     }

    @Override
    public void addIngredients(Meal item) {
        chosenIngredientsFragment.updateRecyclerView(item);
    }


    @Override
    public void showCategories(ArrayList<?> ingredients) {
        allIngredientsFragment.showCategories(ingredients);
    }

    @Override
    public void failedToShowCategories(String errMsg) {
        allIngredientsFragment.failedToShowCategories(errMsg);
    }

    @Override
    public String sendUrl() {
        url="list.php?i=list";
        return  url;
    }
}