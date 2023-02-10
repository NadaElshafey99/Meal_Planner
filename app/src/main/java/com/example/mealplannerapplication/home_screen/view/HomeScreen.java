package com.example.mealplannerapplication.home_screen.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.network.RetrofitClient;

import java.util.ArrayList;
import java.util.Objects;

public class HomeScreen extends Fragment implements NetworkInterface {

    RecyclerView myDailyRec;
    LinearLayoutManager layoutManager;
    DailyAdapter dailyAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        myDailyRec = view.findViewById(R.id.daily_rec);
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myDailyRec.setLayoutManager(layoutManager);
        RetrofitClient retroFitClient = new RetrofitClient(myDailyRec,getContext());
        retroFitClient.startCall(this);
        return view;
    }

    @Override
    public void onSuccess(ArrayList<Meal> meals) {
        dailyAdapter = new DailyAdapter(this.getContext(),meals);
        myDailyRec.setAdapter(dailyAdapter);
    }

    @Override
    public void onFailure(String errMsg) {

    }
}