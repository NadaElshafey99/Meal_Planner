package com.example.mealplannerapplication.searchByIngredients.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Ingredients;

public class SearchByIngredientActivity extends AppCompatActivity implements Communicator {

    private ChosenIngredientsFragment chosenIngredientsFragment;
    private AllIngredientsFragment allIngredientsFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_ingredient);
        chosenIngredientsFragment=new ChosenIngredientsFragment();
        allIngredientsFragment=new AllIngredientsFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.IngredientsRecyclerView,chosenIngredientsFragment);
        fragmentTransaction.add(R.id.ingredientsRecyclerView,allIngredientsFragment);
        fragmentTransaction.commit();
     }

    @Override
    public void addIngredients(Ingredients item) {
        chosenIngredientsFragment.updateRecyclerView(item);
    }
}