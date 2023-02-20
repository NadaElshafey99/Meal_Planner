package com.example.mealplannerapplication.addMealToWeeklyPlan.presenter;

import com.example.mealplannerapplication.addMealToWeeklyPlan.view.AddMealToWeeklyPlannerInterface;
import com.example.mealplannerapplication.db.FirebaseDB;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.RepositoryInterface;
import com.example.mealplannerapplication.network.NetworkInterface;

import java.util.ArrayList;

public class AddMealToWeeklyPlanPresenter implements AddMealToWeeklyPlanPresenterInterface, NetworkInterface {

    private AddMealToWeeklyPlannerInterface addMealToWeeklyPlannerInterface;
    private RepositoryInterface repositoryInterface;
    private FirebaseDB firebaseDB;

    public AddMealToWeeklyPlanPresenter(AddMealToWeeklyPlannerInterface addMealToWeeklyPlannerInterface, RepositoryInterface repositoryInterface, FirebaseDB firebaseDB) {
        this.addMealToWeeklyPlannerInterface = addMealToWeeklyPlannerInterface;
        this.repositoryInterface = repositoryInterface;
        this.firebaseDB = firebaseDB;
    }

    @Override
    public void successToInsertMeal() {
//        addMealToWeeklyPlannerInterface.successToInsertMeal();
    }

    @Override
    public void failedToInsertMeal(String errMsg) {
        addMealToWeeklyPlannerInterface.failedToGetMeal(errMsg);
    }

    @Override
    public void getMealDetails(String Url) {
        repositoryInterface.getUrl(Url);
        repositoryInterface.getData(this);

    }

    @Override
    public void insertMealToDB(Meal meal) {
        repositoryInterface.addMealToPlanner(meal);
    }

    @Override
    public void insertMealToFirebaseDB(Meal meal) {
        repositoryInterface.addMealToFirebasePlanner(meal, firebaseDB);
    }


    @Override
    public void onFailure(String errMsg) {
        addMealToWeeklyPlannerInterface.failedToGetMeal(errMsg);
    }

    @Override
    public void onSuccess(ArrayList<Meal> mealsList) {
        addMealToWeeklyPlannerInterface.getMealDetailsToInsert(mealsList.get(0));
    }

    @Override
    public void onSuccessCategory(ArrayList<Meal> meals) {

    }
}
