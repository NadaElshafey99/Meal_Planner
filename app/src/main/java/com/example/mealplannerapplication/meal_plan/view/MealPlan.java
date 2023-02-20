package com.example.mealplannerapplication.meal_plan.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.mealplannerapplication.HasNetworkConnection;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.db.ConcreteLocalSource;
import com.example.mealplannerapplication.login_screen.view.LoginScreen;
import com.example.mealplannerapplication.meal_details.view.MealDetailsView;
import com.example.mealplannerapplication.meal_plan.presenter.MealPlanPresenter;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;

import java.util.ArrayList;
import java.util.List;


public class MealPlan extends Fragment implements MealPlanScreenInterface{

    private RecyclerView breakFastRecyclerView;
    private RecyclerView launchRecyclerView;
    private static FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;
    private RecyclerView dinnerRecyclerView;
    private ArrayList<Meal> breakFastList;
    private ArrayList<Meal> launchList;
    private ArrayList<Meal> dinnerList;
    private MyBreakfastAdapter breakfastAdapter;
    private MyLaunchAdapter launchAdapter;
    private MyDinnerAdapter dinnerAdapter;
    private RadioGroup radioGroupForDay;
    private RadioButton radioButtonForDay;
    private static MealDetailsView mealDetailsView;
    private MealPlanPresenter mealPlanPresenter;
    private String selectedDay="SAT";
    List<Meal> meals;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        breakFastList=new ArrayList<>();
        launchList=new ArrayList<>();
        dinnerList=new ArrayList<>();
        mealPlanPresenter=new MealPlanPresenter(this,
                Repository.getInstance(ConcreteLocalSource.getInstance(getContext()),
                getContext()));
        if(LoginScreen.isGuest==false) {
            mealPlanPresenter.getWeeklyMeals();
        }
        else {
            Toast.makeText(getContext(), getString(R.string.youAreGuest), Toast.LENGTH_SHORT).show();
        }


        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_meal_plan_update, container, false);
        breakFastRecyclerView =view.findViewById(R.id.breakFast_RecyclerView);
        launchRecyclerView =view.findViewById(R.id.launch_RecyclerView);
        dinnerRecyclerView =view.findViewById(R.id.dinner_RecyclerView);

        breakFastRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        launchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        dinnerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioGroupForDay = view.findViewById(R.id.radioGroupForDays);
        radioGroupForDay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButtonForDay = view.findViewById(checkedId);
                selectedDay = (String) radioButtonForDay.getText();
                showWeeklyMeals(meals);
            }
        });

        breakfastAdapter=new MyBreakfastAdapter(getContext(),breakFastList);
        breakFastRecyclerView.setAdapter(breakfastAdapter);

        launchAdapter=new MyLaunchAdapter(getContext(),launchList);
        launchRecyclerView.setAdapter(launchAdapter);

        dinnerAdapter=new MyDinnerAdapter(getContext(),dinnerList);
        dinnerRecyclerView.setAdapter(dinnerAdapter);
    }

    @Override
    public void showWeeklyMeals(List<Meal> meals) {
        if (meals != null) {
            this.meals = meals;
            breakFastList.clear();
            launchList.clear();
            dinnerList.clear();
            for (int i = 0; i < meals.size(); i++) {
                if (meals.get(i).getMealTime().equals("Breakfast") && meals.get(i).getMealDay().toUpperCase().startsWith(selectedDay)) {
                    breakFastList.add(meals.get(i));
                    breakfastAdapter.setList(breakFastList);
                    breakfastAdapter.notifyDataSetChanged();
                    launchAdapter.setList(launchList);
                    launchAdapter.notifyDataSetChanged();
                    dinnerAdapter.setList(dinnerList);
                    dinnerAdapter.notifyDataSetChanged();
                } else {
                    if (meals.get(i).getMealTime().equals("Launch") && meals.get(i).getMealDay().toUpperCase().startsWith(selectedDay))
                    {
                        launchList.add(meals.get(i));
                        launchAdapter.setList(launchList);
                        breakfastAdapter.notifyDataSetChanged();
                        launchAdapter.setList(launchList);
                        launchAdapter.notifyDataSetChanged();
                        dinnerAdapter.setList(dinnerList);
                        dinnerAdapter.notifyDataSetChanged();
                    } else {
                        if (meals.get(i).getMealTime().equals("Dinner") && meals.get(i).getMealDay().toUpperCase().startsWith(selectedDay))
                            dinnerList.add(meals.get(i));
                        breakfastAdapter.notifyDataSetChanged();
                        launchAdapter.setList(launchList);
                        launchAdapter.notifyDataSetChanged();
                        dinnerAdapter.setList(dinnerList);
                        dinnerAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    /*public void updateRecyclerView(Meal ingredients)
    {
        chosenIngredientList.add(ingredients);
        adapterForChosenIngredients.notifyDataSetChanged();
    }*/
}