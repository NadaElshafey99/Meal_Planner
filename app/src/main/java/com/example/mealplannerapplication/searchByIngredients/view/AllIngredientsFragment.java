package com.example.mealplannerapplication.searchByIngredients.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Ingredients;
import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;

public class AllIngredientsFragment extends Fragment{

    private RecyclerView allIngredientsRecyclerView;
    private ArrayList<? extends Parcelable> allIngredientsList;
    private AdapterForAllIngredients adapterForAllIngredients;
    private Communicator communicator;
    private static final String KEY_ARRAYLIST = "ArrayList";
    private static AllIngredientsFragment allIngredientsFragment=null;

    public AllIngredientsFragment(Communicator communicator)
    {
     this.communicator=communicator;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null)
        {
            AdapterForAllIngredients.ingredientsList = (ArrayList<? extends Parcelable>) savedInstanceState.getParcelableArrayList(KEY_ARRAYLIST);
            adapterForAllIngredients.setList(AdapterForAllIngredients.ingredientsList);
            adapterForAllIngredients.notifyDataSetChanged();
        }
        else {
            allIngredientsList=new ArrayList<>();
        }
    }
    @Override
    public void onSaveInstanceState(@Nullable Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_ARRAYLIST, (ArrayList<? extends Parcelable>) AdapterForAllIngredients.ingredientsList);
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
        adapterForAllIngredients=new AdapterForAllIngredients(getContext(),allIngredientsList,communicator);
        allIngredientsRecyclerView.setAdapter(adapterForAllIngredients);

    }
    public void showCategories(ArrayList<?> ingredients) {
        adapterForAllIngredients.setList(ingredients);
        adapterForAllIngredients.notifyDataSetChanged();
    }

    public void failedToShowCategories(String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
    }

}
