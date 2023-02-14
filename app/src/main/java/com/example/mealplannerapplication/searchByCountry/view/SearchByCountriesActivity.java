package com.example.mealplannerapplication.searchByCountry.view;

import static java.lang.Package.getPackage;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Categories;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.network.RemoteSource;
import com.example.mealplannerapplication.network.RetrofitClient;
import com.example.mealplannerapplication.searchByCountry.presenter.SearchByCountriesPresenter;

import java.util.ArrayList;
import java.util.List;


public class SearchByCountriesActivity extends AppCompatActivity implements SearchByCountriesViewInterface{

    private RecyclerView recyclerViewShowCountries;
    private List<Meal> countryMeals;
    private MyAdapter myAdapter;
    private GridLayoutManager gridLayoutManager;
    private SearchByCountriesPresenter searchByCountriesPresenter;
    private String url;
    static Resources res;
    static String pck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_countries);
        countryMeals=new ArrayList<>();
        myAdapter=new MyAdapter(this,countryMeals);
        res= getResources();
        pck=getPackageName();
        recyclerViewShowCountries=(RecyclerView)findViewById(R.id.recyclerViewCountries);
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerViewShowCountries.setLayoutManager(gridLayoutManager);
        recyclerViewShowCountries.setAdapter(myAdapter);
        searchByCountriesPresenter=new SearchByCountriesPresenter(this,
                Repository.getInstance(RetrofitClient.getInstance(),this));
        searchByCountriesPresenter.getCountries(sendUrl());

    }

    @Override
    public void showCountries(ArrayList<?> countries) {
        myAdapter.setList((List<Meal>) countries);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void failedToShowCategories(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String sendUrl() {
        url="list.php?a=list";
        return url;
    }
}