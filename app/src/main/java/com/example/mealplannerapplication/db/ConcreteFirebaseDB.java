package com.example.mealplannerapplication.db;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.addMealToWeeklyPlan.view.AddMealToWeeklyPlannerInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ConcreteFirebaseDB implements FirebaseDB {

    private FirebaseAuth authentication;
    private FirebaseUser firebaseUser;
    private Repository repository;
    private Context context;

    public ConcreteFirebaseDB(Context context) {
        this.context = context;

    }

    @Override
    public void insertPlanMeal(Meal meal) {

        authentication = FirebaseAuth.getInstance();
        firebaseUser = authentication.getCurrentUser();
        DatabaseReference planFav = FirebaseDatabase.getInstance().getReference().child("Registered Users");
        planFav.child(firebaseUser.getUid()).child("Weekly Planner").child("Id Meal").child(meal.getIdMeal()).push().setValue(meal)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, context.getString(R.string.somethingWentWrong), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void getWeeklyMeals() {
        authentication = FirebaseAuth.getInstance();
        firebaseUser = authentication.getCurrentUser();
        DatabaseReference registered_users = FirebaseDatabase.getInstance().getReference().child("Registered Users");
        DatabaseReference planFav = registered_users.child(firebaseUser.getUid()).child("Weekly Planner").child("Id Meal");
        planFav.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (DataSnapshot meals : snapshot.getChildren()) {
                            Meal mealList = meals.getChildren().iterator().next().getValue(Meal.class);
                            repository = Repository.getInstance(ConcreteLocalSource.getInstance(context), context);
                            repository.addMealToPlanner(mealList);
                        }
                    }
                }).start();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, context.getString(R.string.somethingWentWrong), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
