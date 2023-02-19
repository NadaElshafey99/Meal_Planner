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
import com.example.mealplannerapplication.db.ConcreteLocalSource;
import com.example.mealplannerapplication.fav_screen.view.presenter.FavScreenPresenter;
import com.example.mealplannerapplication.fav_screen.view.presenter.FavScreenPresenterInterface;
import com.example.mealplannerapplication.home_screen.view.OnMealClickListener;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;

import java.util.ArrayList;
import java.util.List;


public class FavoriteScreen extends Fragment implements FavScreenViewInterface, OnMealClickListener {

    RecyclerView favRec;
    FavAdapter favAdapter;
    FavScreenPresenterInterface favPresenterInterface;

    public FavoriteScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favPresenterInterface = new FavScreenPresenter(this,
                Repository.getInstance(ConcreteLocalSource.getInstance(getContext()),
                        getContext()));

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
        favRec.setLayoutManager(new GridLayoutManager(getContext(), 2));
        favAdapter = new FavAdapter(requireContext(), new ArrayList<>(), this);
        favPresenterInterface.getFavMeals();


    }

    @Override
    public void showFavorites(List<Meal> meals) {

        favAdapter.setFavList(meals);
        favAdapter.notifyDataSetChanged();
        favRec.setAdapter(favAdapter);
    }

    @Override
    public void onFavClicked(Meal meal) {
        favPresenterInterface.handleFavMeal(meal);
    }

    @Override
    public void onPlanClicked(Meal meal) {
        favPresenterInterface.handlePlanMeal(meal);
    }
}