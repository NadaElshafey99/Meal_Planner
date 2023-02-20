package com.example.mealplannerapplication.fav_screen.view.presenter;

import com.example.mealplannerapplication.fav_screen.view.FavScreenViewInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.RepositoryInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavScreenPresenter implements FavScreenPresenterInterface{

    FavScreenViewInterface viewInterfaceRef;
    RepositoryInterface repoRef;

    public FavScreenPresenter(FavScreenViewInterface view, RepositoryInterface repoRef) {
        this.viewInterfaceRef = view;
        this.repoRef = repoRef;
    }
    @Override
    public void getFavMeals() {
        repoRef.getFavMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> viewInterfaceRef.showFavorites(meals));
    }

    @Override
    public void addToFav(Meal meal) {
        meal.setFav(true);
        repoRef.addMealToFav(meal);
        System.out.println("meal added to favorite");
    }

    @Override
    public void handleFavMeal(Meal meal) {
       Disposable d = repoRef.getFavMeal(meal.getIdMeal()).firstOrError()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(meal1 -> {
                    if(meal.isFav()) {
                        removeFromFav(meal);
                        System.out.println("Checked");
                    }
                    else {
                        addToFav(meal);
                        System.out.println("Unchecked");

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

    }
}
