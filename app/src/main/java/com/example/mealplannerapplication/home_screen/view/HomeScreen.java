package com.example.mealplannerapplication.home_screen.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.home_screen.view.presenter.HomeScreenPresenter;
import com.example.mealplannerapplication.home_screen.view.presenter.HomeScreenPresenterInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.network.RetrofitClient;

import java.util.ArrayList;

public class HomeScreen extends Fragment implements NetworkInterface {

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

        RetrofitClient retroFitClient = RetrofitClient.getInstance(myDailyRec);
        for (int i = 0; i < 5; i++) {
            retroFitClient.getRandomMeal(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        myDailyRec = view.findViewById(R.id.daily_rec);
        myDailyRec.setLayoutManager(layoutManager);

        return view;
    }


    @Override
    public void onSuccess(ArrayList<Meal> meals) {
        mealArrayList.add(meals.get(0));
        dailyAdapter = new DailyAdapter(this.getContext(), mealArrayList);
        myDailyRec.setAdapter(dailyAdapter);
    }

    @Override
    public void onFailure(String errMsg) {

    }
}