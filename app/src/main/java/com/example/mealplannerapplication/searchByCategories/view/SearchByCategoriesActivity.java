package com.example.mealplannerapplication.searchByCategories.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Categories;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.model.RepositoryInterface;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.network.RemoteSource;
import com.example.mealplannerapplication.network.RetrofitClient;
import com.example.mealplannerapplication.searchByCategories.presenter.SearchByCategoriesPresenter;

import java.util.ArrayList;
import java.util.List;


public class SearchByCategoriesActivity extends AppCompatActivity implements SearchByCategoriesScreenInterface{

    private RecyclerView recyclerViewShowCategories;
    private List<Meal> categories;
    private MyAdapter myAdapter;
    private GridLayoutManager gridLayoutManager;
    private String url;
    private SearchByCategoriesPresenter searchByCategoriesPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_categories);
        categories=new ArrayList<>();
        myAdapter=new MyAdapter(this,categories);
        recyclerViewShowCategories=(RecyclerView)findViewById(R.id.recyclerViewCategories);
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerViewShowCategories.setLayoutManager(gridLayoutManager);
        recyclerViewShowCategories.setAdapter(myAdapter);
        searchByCategoriesPresenter=new SearchByCategoriesPresenter
                (this,
                        Repository.getInstance(RetrofitClient.getInstance(),this)
                );
        searchByCategoriesPresenter.getCategories(sendUrl());
    }
    @Override
    public void showCategories(ArrayList<?> categories) {
        myAdapter.setList(categories);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void failedToShowCategories(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String sendUrl() {
        url="list.php?c=list";
        return url;
    }

}