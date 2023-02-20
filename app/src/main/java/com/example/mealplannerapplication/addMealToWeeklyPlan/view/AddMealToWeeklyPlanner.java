package com.example.mealplannerapplication.addMealToWeeklyPlan.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mealplannerapplication.NavigationActivity;
import com.example.mealplannerapplication.addMealToWeeklyPlan.*;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.addMealToWeeklyPlan.presenter.AddMealToWeeklyPlanPresenter;
import com.example.mealplannerapplication.db.ConcreteFirebaseDB;
import com.example.mealplannerapplication.db.ConcreteLocalSource;
import com.example.mealplannerapplication.db.FirebaseDB;
import com.example.mealplannerapplication.meal_details.presenter.MealDetailsPresenter;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.network.RetrofitClient;
import com.google.firebase.auth.FirebaseUser;

public class AddMealToWeeklyPlanner extends Fragment implements AddMealToWeeklyPlannerInterface {

    private String idMeal;
    private String urlImage;
    private String mealName;
    private ImageView backButton;
    private TextView tv_mealName;
    private ImageView mealImage;
    private RadioGroup radioGroupForTime;
    private RadioButton radioButtonForTime;
    private RadioGroup radioGroupForDay;
    private RadioButton radioButtonForDay;

    private Button addToPlanBtn;
    private String selectedTime;
    private String selectedDay;
    private String url;
    private Meal meal;
    private FirebaseDB firebaseDB;
    private AddMealToWeeklyPlanPresenter addMealToWeeklyPlanPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idMeal = getArguments().getString("IdMeal");
            urlImage = getArguments().getString("urlImage");
            mealName = getArguments().getString("mealName");
        }
        firebaseDB = new ConcreteFirebaseDB(getContext());
        addMealToWeeklyPlanPresenter = new AddMealToWeeklyPlanPresenter(
                this,
                Repository.getInstance(RetrofitClient.getInstance(),
                        ConcreteLocalSource.getInstance(getContext()), getContext()), firebaseDB);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_meal_to_weekly_planner, container, false);
        backButton = view.findViewById(R.id.back_arrow);
        mealImage = view.findViewById(R.id.meal_image);
        addToPlanBtn = view.findViewById(R.id.addToPlanButton);
        tv_mealName = view.findViewById(R.id.tv_mealName);
        radioGroupForTime = view.findViewById(R.id.radioGroupForTime);
        radioGroupForDay = view.findViewById(R.id.radio_grp);
        radioGroupForTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButtonForTime = view.findViewById(checkedId);
                selectedTime = (String) radioButtonForTime.getText();
            }
        });
        radioGroupForDay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButtonForDay = view.findViewById(checkedId);
                selectedDay = (String) radioButtonForDay.getText();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        addToPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((radioButtonForDay == null || radioButtonForTime == null)) {
                    Toast.makeText(getContext(), getString(R.string.pleaseCheckTimeAndData), Toast.LENGTH_SHORT).show();
                } else {
                    if ((radioButtonForDay.isChecked() && radioButtonForTime.isChecked())) {
                        addMealToWeeklyPlanPresenter.getMealDetails(sendUrl());
                    } else {
                        Toast.makeText(getContext(), getString(R.string.pleaseCheckTimeAndData), Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        tv_mealName.setText(mealName);
        Glide.with(getContext()).load(urlImage)
                .placeholder(new ColorDrawable(Color.TRANSPARENT))
                .into(mealImage);

    }

    @Override
    public void getMealDetailsToInsert(Meal meal) {
        this.meal = meal;
        successToInsertMeal();
    }

    @Override
    public void failedToGetMeal(String errMsg) {
        Toast.makeText(getContext(), getString(R.string.somethingWentWrong), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String sendUrl() {
        url = "lookup.php?i=" + idMeal;
        return url;
    }

    @Override
    public void insertMealToDB(Meal meal) {
        addMealToWeeklyPlanPresenter.insertMealToDB(meal);
    }

    @Override
    public void insertMealToFirebase(Meal meal) {
        addMealToWeeklyPlanPresenter.insertMealToFirebaseDB(meal);
    }

    @Override
    public void successToInsertMeal() {
        meal.setFav(false);
        meal.setMealDay(selectedDay);
        meal.setMealTime(selectedTime);
        new Thread(new Runnable() {
            @Override
            public void run() {

                insertMealToDB(meal);
                insertMealToFirebase(meal);
            }
        }).start();
        Toast.makeText(getContext(), getString(R.string.success), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), NavigationActivity.class);
        startActivity(intent);
    }

}