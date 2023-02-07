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
import com.example.mealplannerapplication.model.Ingredients;
import com.example.mealplannerapplication.resultFromSearchView.view.ResultFromSearchActivity;
import com.example.mealplannerapplication.searchByCategories.view.SearchByCategoriesActivity;
import com.example.mealplannerapplication.searchByCountry.view.SearchByCountriesActivity;
import com.example.mealplannerapplication.searchByIngredients.view.SearchByIngredientActivity;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    private RecyclerView ingredientsRecyclerView;
    private RecyclerView categoriesRecyclerView;
    private RecyclerView countriesRecyclerView;
    private ArrayList<Ingredients> allIngredientsList;
    private MyAdapter myAdapter;
    private AdapterForIngredients adapterForIngredients;
    Button viewIngredients_btn;
    Button viewCategories_btn;
    Button viewCountries_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allIngredientsList=new ArrayList<>();
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
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Chicken","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Chicken","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));

        myAdapter =new MyAdapter(getContext(),allIngredientsList);
        countriesRecyclerView.setAdapter(myAdapter);

        myAdapter =new MyAdapter(getContext(),allIngredientsList);
        categoriesRecyclerView.setAdapter(myAdapter);

        adapterForIngredients =new AdapterForIngredients(getContext(),allIngredientsList);
        ingredientsRecyclerView.setAdapter(adapterForIngredients);
        viewIngredients_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SearchByIngredientActivity.class);
                startActivity(intent);
            }
        });
        viewCategories_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SearchByCategoriesActivity.class);
                startActivity(intent);
            }
        });
        viewCountries_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(getActivity(), SearchByCountriesActivity.class);
                Intent intent=new Intent(getActivity(), ResultFromSearchActivity.class);
                startActivity(intent);
            }
        });
    }
}