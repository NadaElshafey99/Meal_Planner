package com.example.mealplannerapplication.searchByIngredients.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Ingredients;
import java.util.ArrayList;

public class AllIngredientsFragment extends Fragment {

    private RecyclerView allIngredientsRecyclerView;
    private ArrayList<Ingredients> allIngredientsList;
    private AdapterForAllIngredients adapterForAllIngredients;
    private Communicator communicator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allIngredientsList=new ArrayList<>();
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Chicken","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Chicken","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Chicken","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Chicken","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Chicken","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Chicken","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Chicken","https://www.themealdb.com/images/category/beef.png"));
        allIngredientsList.add(new Ingredients("Beef","https://www.themealdb.com/images/category/beef.png"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_all_ingredients, container, false);
        allIngredientsRecyclerView =view.findViewById(R.id.ingredientsFragment);
        allIngredientsRecyclerView.setLayoutManager(new GridLayoutManager(AllIngredientsFragment.super.getContext(),3));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicator=(SearchByIngredientActivity) getActivity();
        adapterForAllIngredients=new AdapterForAllIngredients(AllIngredientsFragment.super.getContext(),allIngredientsList,communicator);
        allIngredientsRecyclerView.setAdapter(adapterForAllIngredients);

    }
}