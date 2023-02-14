package com.example.mealplannerapplication.resultFromSearchView.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Categories;
import com.example.mealplannerapplication.search.view.SearchFragment;

import java.util.ArrayList;

public class ResultFromSearchActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ResultFromSearchFragment resultFromSearchFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_from_search);
        resultFromSearchFragment=new ResultFromSearchFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentResultContainerView,resultFromSearchFragment);
        fragmentTransaction.commit();
    }

}