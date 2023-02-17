package com.example.mealplannerapplication.home_screen.presenter;

import android.util.Log;

import com.example.mealplannerapplication.home_screen.view.HomeScreenViewInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.RepositoryInterface;
import com.example.mealplannerapplication.network.NetworkInterface;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeScreenPresenter implements HomeScreenPresenterInterface, NetworkInterface {

    HomeScreenViewInterface viewInterfaceRef;
    RepositoryInterface repoRef;
    ArrayList<Meal> mealArrayList = new ArrayList<>();


    public HomeScreenPresenter(HomeScreenViewInterface view, RepositoryInterface repoRef) {
        this.viewInterfaceRef = view;
        this.repoRef = repoRef;
    }

    @Override
    public void getDailyMeals() {
        for (int i = 0; i < 5; i++) {
            repoRef.getUrl("random.php/");
            repoRef.getData(this);
        }

    }


    public void getBeefCategory() {
        Disposable d = repoRef.getMealByCategory("Beef").subscribe(i -> viewInterfaceRef.showBeefCategory(i.getMeals()));
    }

    @Override
    public void getBreakfastCategory() {
        Disposable d = repoRef.getMealByCategory("Breakfast").subscribe(i -> viewInterfaceRef.breakfast(i.getMeals()));
    }

    @Override
    public void getChickenCategory() {
        Disposable d = repoRef.getMealByCategory("Chicken").subscribe(i -> viewInterfaceRef.showChickenCategory(i.getMeals()));
    }

    @Override
    public void getDesertCategory() {
        Disposable d = repoRef.getMealByCategory("Dessert").subscribe(i -> viewInterfaceRef.showDesertCategory(i.getMeals()));
    }


    @Override
    public void addToFav(Meal meal) {
        meal.setFav(true);
        repoRef.addMealToFav(meal);
        System.out.println("meal added to favorite");
    }

    @Override
    public void handleFavMeal(Meal meal) {
        repoRef.getFavMeal(meal.getIdMeal())
                .firstOrError()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Meal>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("subscribed");
                        repoRef.addMealToFav(meal);
                    }

                    @Override
                    public void onSuccess(Meal favMeal) {
                        // The meal is in the database
                        if (!meal.isFav()) {
                            addToFav(meal);
                            System.out.println("Checked");
                        } else {
                            removeFromFav(meal);
                            System.out.println("Unchecked");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Error");
                    }
                });
    }

    @Override
    public void removeFromFav(Meal meal) {
        meal.setFav(false);
        repoRef.removeMealFromFav(meal);
        System.out.println("meal deleted from favorite");
    }

    @Override
    public void handlePlanMeal(Meal meal) {
        System.out.println("meal added to plan");
    }


    @Override
    public void onSuccess(ArrayList<Meal> list) {
        mealArrayList.add(list.get(0));
        viewInterfaceRef.showDailyMeals(mealArrayList);
    }

    @Override
    public void onSuccessCategory(ArrayList<Meal> meals) {
        viewInterfaceRef.showChickenCategory(meals);
    }

    @Override
    public void onFailure(String errMsg) {
        Log.i("TAG", "onFailure: ");
    }
}
