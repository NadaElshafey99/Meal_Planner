package com.example.mealplannerapplication.search.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Ingredients;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView ingredientsRecyclerView;
    private RecyclerView categoriesRecyclerView;
    private RecyclerView countriesRecyclerView;
    private ArrayList<Ingredients> allIngredientsList;
    private ArrayList<Ingredients> chosenIngredientList;
    private AdapterForCountry adapterForCountry;
    private AdapterForIngredients adapterForIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ingredientsRecyclerView =(RecyclerView)findViewById(R.id.IngredientsRecyclerView);
        categoriesRecyclerView =(RecyclerView)findViewById(R.id.CategoriesRecyclerView);
        countriesRecyclerView=(RecyclerView)findViewById(R.id.CountriesRecyclerView);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        countriesRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        allIngredientsList=new ArrayList<>();
        chosenIngredientList=new ArrayList<>();
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Chicken","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Chicken","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));

        adapterForCountry =new AdapterForCountry(this,allIngredientsList);
        countriesRecyclerView.setAdapter(adapterForCountry);

        adapterForCountry =new AdapterForCountry(this,allIngredientsList);
        categoriesRecyclerView.setAdapter(adapterForCountry);

        adapterForIngredients =new AdapterForIngredients(this,allIngredientsList);
        ingredientsRecyclerView.setAdapter(adapterForIngredients);

    }

}