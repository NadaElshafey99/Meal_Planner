package com.example.mealplannerapplication.resultFromSearchView.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Categories;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.network.RetrofitClient;
import com.example.mealplannerapplication.resultFromSearchView.presenter.ResultFromSearchPresenter;
import com.example.mealplannerapplication.searchByCategories.presenter.SearchByCategoriesPresenter;
import com.example.mealplannerapplication.searchByCategories.view.SearchByCategoriesScreenInterface;

import java.util.ArrayList;

public class ResultFromSearchFragment extends Fragment implements SearchByCategoriesScreenInterface {

    RecyclerView resultRecyclerView;
    MyAdapter myAdapter;
    GridLayoutManager  gridLayoutManager;
    ArrayList<Meal> categories;
    private String url;
    private ResultFromSearchPresenter resultFromSearchPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categories=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if(bundle != null){
            url=bundle.getString("url");
        }
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_result_from_search, container, false);
        resultRecyclerView=view.findViewById(R.id.resultRecyclerView);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        resultRecyclerView.setLayoutManager(gridLayoutManager);
        myAdapter=new MyAdapter(getContext(),categories);
        resultRecyclerView.setAdapter(myAdapter);
        resultFromSearchPresenter=new ResultFromSearchPresenter
                (this,
                        Repository.getInstance(RetrofitClient.getInstance(),getContext())
                );
        resultFromSearchPresenter.getCategoriesSelected(sendUrl());
    }

    @Override
    public void showCategories(ArrayList<?> categories) {
        myAdapter.setList(categories);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void failedToShowCategories(String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String sendUrl() {
        return url;
    }
}