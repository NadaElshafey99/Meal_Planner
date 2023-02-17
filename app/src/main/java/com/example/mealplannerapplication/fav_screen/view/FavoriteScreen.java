package com.example.mealplannerapplication.fav_screen.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.home_screen.view.OnMealClickListener;
import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;


public class FavoriteScreen extends Fragment implements FavScreenViewInterface, OnMealClickListener {

    RecyclerView favRec;
    //FavAdapter favAdapter;
    public FavoriteScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favRec = view.findViewById(R.id.fav_rec);
        //favAdapter = new FavAdapter(requireContext(),new ArrayList<>(),this);
        favRec.setLayoutManager(new GridLayoutManager(getContext(),2));

    }

    @Override
    public void showFavorites(ArrayList<Meal> meals) {
//        favAdapter.setFavList(meals);
//        favAdapter.notifyDataSetChanged();
//        favRec.setAdapter(favAdapter);
    }

    @Override
    public void onFavClicked(Meal meal) {

    }

    @Override
    public void onPlanClicked(Meal meal) {

    }
}