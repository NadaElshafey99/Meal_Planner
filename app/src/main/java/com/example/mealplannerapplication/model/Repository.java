package com.example.mealplannerapplication.model;

import android.content.Context;

import com.example.mealplannerapplication.db.FirebaseDB;
import com.example.mealplannerapplication.db.LocalSource;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.network.RemoteSource;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;


public class Repository implements RepositoryInterface {

    private Context context;
    private RemoteSource remoteSource;
    private FirebaseDB firebaseDB;
    private LocalSource localSource;
    private static Repository repository = null;


    public static Repository getInstance(RemoteSource remoteSource,LocalSource localSource,Context context) {
        if (repository == null) {
            repository = new Repository(remoteSource,localSource,context);
        }
        return repository;
    }

    public static Repository getInstance(RemoteSource remoteSource,Context context) {
        if (repository == null) {
            repository = new Repository(remoteSource,context);
        }
        return repository;
    }


    public static Repository getInstance(LocalSource localSource,Context context) {
        if (repository == null) {
            repository = new Repository(localSource,context);
        }
        return repository;
    }

    private Repository(RemoteSource remoteSource,LocalSource localSource, Context context)
    {
        this.remoteSource=remoteSource;
        this.context=context;
        this.localSource=localSource;
    }

    private Repository(RemoteSource remoteSource, Context context)
    {
        this.remoteSource=remoteSource;
        this.context=context;
    }

    private Repository(LocalSource localSource, Context context)
    {
        this.localSource=localSource;
        this.context=context;
    }

    @Override
    public void getData(NetworkInterface networkInterfaceRef) {
        remoteSource.enqueueCall(networkInterfaceRef);
    }

    @Override
    public Observable<Root> getMealByCategory(String category) {
        return remoteSource.getMealsByCategories(category);
    }

    @Override
    public void getUrl(String url) {
        remoteSource.getUrl(url);
    }

    @Override
    public Flowable<List<Meal>> getFavMeals() {
       return localSource.getAllFavMeals();
    }

    @Override
    public Flowable<List<Meal>> getWeeklyMeals() {
        return localSource.getAllWeeklyMeals();
    }

    @Override
    public Flowable<Meal> getFavMeal(String id) {
        return localSource.getFavMeal(id);
    }

    @Override
    public void addMealToFav(Meal meal) {
        localSource.insertFavMeal(meal);
    }

    @Override
    public void addMealToPlanner(Meal meal) {
        localSource.insertPlanMeal(meal);
    }

    @Override
    public void addMealToFirebasePlanner(Meal meal,FirebaseDB firebaseDB) {
        this.firebaseDB=firebaseDB;
        this.firebaseDB.insertPlanMeal(meal);
        
    }

    @Override
    public void removeMealFromFav(Meal meal) {
        localSource.insertFavMeal(meal);
    }

    @Override
    public Flowable<Meal> getPlanMeal(String id) {
        return null;
    }

}
