package com.example.mealplannerapplication;

import com.example.mealplannerapplication.model.Meal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;

public class BackupMeal {

    private DocumentReference doc;
    public void backupMeals(List<Meal> meals){
        for(Meal meal : meals) {
            HashMap<String, Meal> mealMap = new HashMap<>();
            mealMap.put(meal.getIdMeal(), meal);
            if (meal.isFav()) {
                doc = FirebaseFirestore.getInstance().document("mealPlanner/users/" + FirebaseAuth.getInstance().getUid() + "/favorites/");

            }
            doc.collection(meal.getStrMeal()).document(meal.getIdMeal()).set(mealMap, SetOptions.merge()).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    System.out.println("Saved");

                } else {
                    System.out.println("Failed");
                }
            });
        }

    }

    public List<Meal> restoreMeals(){
        doc =FirebaseFirestore.getInstance().document("mealPlanner/users/" + FirebaseAuth.getInstance().getUid() + "/favorites/");
        return null; //todo
    }

}