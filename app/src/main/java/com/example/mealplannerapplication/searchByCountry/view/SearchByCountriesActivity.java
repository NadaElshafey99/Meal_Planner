package com.example.mealplannerapplication.searchByCountry.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Categories;
import com.example.mealplannerapplication.searchByCategories.view.MyAdapter;

import java.util.ArrayList;
import java.util.List;


public class SearchByCountriesActivity extends AppCompatActivity {

    RecyclerView recyclerViewShowCategories;
    List<Categories> categories;
    com.example.mealplannerapplication.searchByCategories.view.MyAdapter myAdapter;
    GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_countries);
        categories=new ArrayList<>();
        categories.add(new Categories("1","Beef","https://www.themealdb.com/images/category/beef.png"));
        categories.add(new Categories("2","Chicken","https://www.themealdb.com/images/category/chicken.png"));
        categories.add(new Categories("1","Beef","https://www.themealdb.com/images/category/beef.png"));
        categories.add(new Categories("2","Chicken","https://www.themealdb.com/images/category/chicken.png"));
        categories.add(new Categories("2","Chicken","https://www.themealdb.com/images/category/chicken.png"));
        categories.add(new Categories("1","Beef","https://www.themealdb.com/images/category/beef.png"));
        myAdapter=new MyAdapter(this,categories);
        recyclerViewShowCategories=(RecyclerView)findViewById(R.id.recyclerViewCountries);
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerViewShowCategories.setLayoutManager(gridLayoutManager);
        recyclerViewShowCategories.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

}