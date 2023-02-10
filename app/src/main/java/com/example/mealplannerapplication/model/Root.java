package com.example.mealplannerapplication.model;

import java.util.ArrayList;
import java.util.List;

public class Root {
    private ArrayList<Meal> meals = new ArrayList<>();
    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }
}
