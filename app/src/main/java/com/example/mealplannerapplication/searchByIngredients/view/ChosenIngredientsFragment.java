package com.example.mealplannerapplication.searchByIngredients.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Ingredients;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.resultFromSearchView.view.ResultFromSearchFragment;

import java.util.ArrayList;

public class ChosenIngredientsFragment extends Fragment {

    private RecyclerView ingredientsChosenRecyclerView;
    private ArrayList<Meal> chosenIngredientList;
    private AdapterForChosenIngredients adapterForChosenIngredients;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chosenIngredientList=new ArrayList<>();
//
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chosen_ingredients, container, false);
        ingredientsChosenRecyclerView =view.findViewById(R.id.chosenFragment);
        ingredientsChosenRecyclerView.setLayoutManager(new LinearLayoutManager(ChosenIngredientsFragment.super.getContext(),LinearLayoutManager.HORIZONTAL,false));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapterForChosenIngredients=new AdapterForChosenIngredients(ChosenIngredientsFragment.super.getContext(),chosenIngredientList);
        ingredientsChosenRecyclerView.setAdapter(adapterForChosenIngredients);

    }
    public void updateRecyclerView(Meal ingredients)
    {
        chosenIngredientList.add(ingredients);
        adapterForChosenIngredients.notifyDataSetChanged();
    }

}