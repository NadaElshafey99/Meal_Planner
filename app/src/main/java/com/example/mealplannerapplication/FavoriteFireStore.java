package com.example.mealplannerapplication;

import android.content.Context;
import android.util.Log;

import com.example.mealplannerapplication.db.ConcreteLocalSource;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.model.Root;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FavoriteFireStore {

    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private static final String favoritesPath = "/favorite/meals/";
    private DocumentReference doc;
    private static FavoriteFireStore firestoreBackup;


    private FavoriteFireStore() {
    }

    public static synchronized FavoriteFireStore getInstance() {
        if (firestoreBackup == null)
            firestoreBackup = new FavoriteFireStore();
        return firestoreBackup;
    }

    public void backupMeals(List<Meal> mealList){
        Root myMeals = new Root();
        ArrayList<Meal> myMealsArray = new ArrayList<>(mealList);

        myMeals.setMeals(myMealsArray);
        firestore.document(currentUserPath() + favoritesPath).set(myMeals).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.i("TAG", "onComplete: success");
            } else {
                Log.i("TAG", "onComplete: fail");
            }
        });
//        mealList.forEach(this::backupMeal);
    }



    public void retrieveMeals(Context context) {
        firestore.document(currentUserPath() + favoritesPath).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Root list = documentSnapshot.toObject(Root.class);
                if(list !=null) {
                    setList(list.getMeals(), context);
                    Log.i("TAG", "onSuccess: retrieved" + list.getMeals().get(0).getStrMeal());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i("Retrieve", "onFailure: " + e.getMessage());
            }
        });

    }
    private void setList(List<Meal> list,Context context){
        list.forEach(meal -> Log.i("TAG", "setList: "+meal.getStrMeal()) );
        Repository repository = Repository.getInstance(ConcreteLocalSource.getInstance(context),context);
        list.forEach(repository::addMealToFav);
    }
    private String currentUserPath() {
        return "mealPlanner/" + FirebaseAuth.getInstance().getUid();
    }

}