package com.example.mealplannerapplication.search.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.SearchByGroupActivity;
import com.example.mealplannerapplication.model.Ingredients;
import com.example.mealplannerapplication.resultFromSearchView.view.ResultFromSearchActivity;
import com.example.mealplannerapplication.searchByCountry.view.SearchByCountriesFragment;
//import com.example.mealplannerapplication.searchByCategories.view.SearchByCategoriesActivity;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    private RecyclerView ingredientsRecyclerView;
    private RecyclerView categoriesRecyclerView;
    private RecyclerView countriesRecyclerView;
    private ArrayList<Ingredients> sampleIngredientsList;
    private ArrayList<Ingredients> sampleCategoriesList;
    private ArrayList<Ingredients> sampleCountriesList;
    private MyAdapter myAdapter;
    private AdapterForIngredients adapterForIngredients;
    public static final String FRAGMENT_NAME="FRAGMENT_NAME";
    Button viewIngredients_btn;
    Button viewCategories_btn;
    Button viewCountries_btn;
//arr.subList(0, 4);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sampleIngredientsList=new ArrayList<>();
        sampleCategoriesList=new ArrayList<>();
        sampleCountriesList=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        ingredientsRecyclerView =view.findViewById(R.id.ingredients_RecyclerView);
        categoriesRecyclerView =view.findViewById(R.id.categories_RecyclerView);
        countriesRecyclerView=view.findViewById(R.id.countries_RecyclerView);
        viewIngredients_btn=view.findViewById(R.id.viewAll_ingredients_btn);
        viewCategories_btn=view.findViewById(R.id.viewAll_categories_btn);
        viewCountries_btn=view.findViewById(R.id.viewAll_countries_btn);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        countriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sampleCategoriesList.add(new Ingredients("Chicken","https://www.themealdb.com/images/ingredients/Chicken.png"));
        sampleCategoriesList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));
        sampleCategoriesList.add(new Ingredients("Dessert","https://www.themealdb.com/images/category/dessert.png"));
        sampleCategoriesList.add(new Ingredients("Pasta","https://www.themealdb.com/images/category/pasta.png"));
        myAdapter =new MyAdapter(getContext(),sampleCategoriesList,"categories");
        categoriesRecyclerView.setAdapter(myAdapter);


        sampleCountriesList.add(new Ingredients("Australia","https://www.worldometers.info/img/flags/as-flag.gif"));
        sampleCountriesList.add(new Ingredients("China","https://www.worldometers.info/img/flags/ch-flag.gif"));
        sampleCountriesList.add(new Ingredients("Egypt","https://www.worldometers.info/img/flags/eg-flag.gif"));
        sampleCountriesList.add(new Ingredients("Italy","https://www.worldometers.info/img/flags/it-flag.gif"));
        myAdapter =new MyAdapter(getContext(),sampleCountriesList,"countries");
        countriesRecyclerView.setAdapter(myAdapter);


        sampleIngredientsList.add(new Ingredients("Avocado","https://www.themealdb.com/images/ingredients/avocado.png"));
        sampleIngredientsList.add(new Ingredients("Cheese","https://www.themealdb.com/images/ingredients/cheese.png"));
        sampleIngredientsList.add(new Ingredients("Egg","https://www.themealdb.com/images/ingredients/egg.png"));
        sampleIngredientsList.add(new Ingredients("Salmon","https://www.themealdb.com/images/ingredients/salmon.png"));
        sampleIngredientsList.add(new Ingredients("Tomatoes","https://www.themealdb.com/images/ingredients/tomatoes.png"));
        adapterForIngredients =new AdapterForIngredients(getContext(),sampleIngredientsList);
        ingredientsRecyclerView.setAdapter(adapterForIngredients);

        viewIngredients_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchByGroupActivity.class);
                intent.putExtra(FRAGMENT_NAME, "searchByIngredientFragment");
                startActivity(intent);
            }
        });

        viewCategories_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchByGroupActivity.class);
                intent.putExtra(FRAGMENT_NAME, "searchByCategoriesFragment");
                startActivity(intent);

            }
        });

        viewCountries_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchByGroupActivity.class);
                intent.putExtra(FRAGMENT_NAME, "searchByCountriesFragment");
                startActivity(intent);
            }
        });
    }
}