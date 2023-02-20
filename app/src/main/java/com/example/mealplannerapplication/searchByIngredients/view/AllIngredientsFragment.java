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

import com.airbnb.lottie.L;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Ingredients;
import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;

public class AllIngredientsFragment extends Fragment{

    private RecyclerView allIngredientsRecyclerView;
    private List<Meal> allIngredientsList;
    private List<Meal> filteredList;

    private AdapterForAllIngredients adapterForAllIngredients;
    private Communicator communicator;
    private static final String KEY_ARRAYLIST = "ArrayList";

    public AllIngredientsFragment(Communicator communicator)
    {
     this.communicator=communicator;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            allIngredientsList=new ArrayList<>();
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
    public void showIngredients(ArrayList<Meal> ingredients) {
        this.allIngredientsList=ingredients;
        adapterForAllIngredients.setList(ingredients);
        adapterForAllIngredients.notifyDataSetChanged();
    }

    public void failedToShowIngredients(String errMsg) {
        Toast.makeText(getContext(), getString(R.string.somethingWentWrong), Toast.LENGTH_SHORT).show();
    }

    public void filterList(Observable<CharSequence> observable) {
        observable.subscribe(c->{
            filteredList=new ArrayList<>(allIngredientsList
                    .stream()
                    .filter(i-> i.getStrIngredient().toLowerCase().startsWith(c.toString()))
//                    .filter(i->c.toString().equalsIgnoreCase(i.getStrCategory()))
                    .collect(Collectors.toList()));
            adapterForAllIngredients.setList(filteredList);
            adapterForAllIngredients.notifyDataSetChanged();

        });
    }
}
