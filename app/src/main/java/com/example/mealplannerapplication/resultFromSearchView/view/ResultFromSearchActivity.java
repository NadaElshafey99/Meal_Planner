package com.example.mealplannerapplication.resultFromSearchView.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Categories;

import java.util.ArrayList;

public class ResultFromSearchActivity extends AppCompatActivity {

    RecyclerView resultRecyclerView;
    MyAdapter myAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Categories> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_from_search);
        resultRecyclerView=findViewById(R.id.resultRecyclerView);
        categories=new ArrayList<>();
        categories.add(new Categories("1","Beef","https://www.themealdb.com/images/category/beef.png"));
        categories.add(new Categories("2","Chicken","https://www.themealdb.com/images/category/chicken.png"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        resultRecyclerView.setLayoutManager(linearLayoutManager);
        myAdapter=new MyAdapter(this,categories);
        resultRecyclerView.setAdapter(myAdapter);
    }
}