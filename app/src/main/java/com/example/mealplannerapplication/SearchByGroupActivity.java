package com.example.mealplannerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.mealplannerapplication.meal_details.view.MealDetailsView;
import com.example.mealplannerapplication.search.view.SearchFragment;
import com.example.mealplannerapplication.searchByCategories.view.SearchByCategoriesFragment;
import com.example.mealplannerapplication.searchByCountry.view.SearchByCountriesFragment;
import com.example.mealplannerapplication.searchByIngredients.view.SearchByIngredientFragment;

public class SearchByGroupActivity extends AppCompatActivity {

    private SearchByCategoriesFragment searchByCategoriesFragment;
    private SearchByCountriesFragment searchByCountriesFragment;
    private SearchByIngredientFragment searchByIngredientFragment;
    private MealDetailsView mealDetailsView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        if (savedInstanceState != null) {
            searchByIngredientFragment = (SearchByIngredientFragment) getSupportFragmentManager().getFragment(savedInstanceState, "myFragmentName");
        }
        else
        {
            searchByIngredientFragment=new SearchByIngredientFragment();
        }
        Intent intent = getIntent();
        String fragment = intent.getExtras().getString(SearchFragment.FRAGMENT_NAME);
        String idMeal = intent.getExtras().getString("IdMeal");
        switch(fragment){

            case "searchByCategoriesFragment":
                searchByCategoriesFragment=new SearchByCategoriesFragment();
                fragmentTransaction
                        .add(R.id.fragmentContainerView,searchByCategoriesFragment );
                fragmentTransaction.commit();
                break;

            case "searchByCountriesFragment":
                searchByCountriesFragment=new SearchByCountriesFragment();
                fragmentTransaction
                        .add(R.id.fragmentContainerView,searchByCountriesFragment);
                fragmentTransaction.commit();
                break;

            case "searchByIngredientFragment":
                fragmentTransaction
                        .add(R.id.fragmentContainerView,searchByIngredientFragment);
                fragmentTransaction.commit();
                break;

            case "mealDetailsFragment":
                mealDetailsView=new MealDetailsView();
                if(idMeal!=null)
                {
                    Bundle bundle=new Bundle();
                    bundle.putString("IdMeal",idMeal);
                    mealDetailsView.setArguments(bundle);
                }
                fragmentTransaction
                        .add(R.id.fragmentContainerView,mealDetailsView );
                fragmentTransaction.commit();
                break;
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       /* if(searchByIngredientFragment!=null) {
            getSupportFragmentManager().putFragment(outState, "myFragmentName", searchByIngredientFragment);
        }*/
    }
}