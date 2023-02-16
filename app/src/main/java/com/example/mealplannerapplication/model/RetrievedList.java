package com.example.mealplannerapplication.model;

import java.util.ArrayList;

public class RetrievedList {

    private ArrayList<Categories> categories;
    private ArrayList<Meal> meals;

    public RetrievedList()
    {

    }

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public ArrayList<Categories> getCategoriesList() {
        return categories;
    }

    public void setCategoriesList(ArrayList<Categories> categories) {
        this.categories = categories;
    }
}
