package com.example.mealplannerapplication.home_screen.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.home_screen.view.presenter.HomeScreenPresenter;
import com.example.mealplannerapplication.home_screen.view.presenter.HomeScreenPresenterInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.network.RetrofitClient;

import java.util.ArrayList;

public class HomeScreen extends Fragment implements HomeScreenViewInterface {

    RecyclerView myDailyRec;
    LinearLayoutManager layoutManager;
    DailyAdapter dailyAdapter;
    ArrayList<Meal> mealArrayList;
    HomeScreenPresenterInterface presenterInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mealArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        presenterInterface = new HomeScreenPresenter(this, Repository.getInstance(RetrofitClient.getInstance()));
        presenterInterface.getDailyMeals();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        myDailyRec = view.findViewById(R.id.daily_rec);
        myDailyRec.setLayoutManager(layoutManager);
        dailyAdapter = new DailyAdapter(this.getContext(), new ArrayList<>());
        myDailyRec.setAdapter(dailyAdapter);
        return view;
    }


    @Override
    public void showData(ArrayList<Meal> meals) {
        Log.i("TAG", "showData: " + meals.get(0).getStrArea());
        dailyAdapter.setList(meals);

        dailyAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMeal(Meal meal) {

    }
}