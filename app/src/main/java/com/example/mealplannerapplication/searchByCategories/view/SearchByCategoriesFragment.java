package com.example.mealplannerapplication.searchByCategories.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.network.RetrofitClient;
import com.example.mealplannerapplication.resultFromSearchView.view.ResultFromSearchFragment;
import com.example.mealplannerapplication.searchByCategories.presenter.SearchByCategoriesPresenter;
import com.google.android.material.internal.TextWatcherAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchByCategoriesFragment extends Fragment implements SearchByCategoriesScreenInterface{

    private RecyclerView recyclerViewShowCategories;
    private List<Meal> categories;
    private MyAdapter myAdapter;
    private String url;
    protected static EditText editTextSearchByCategories;
    private SearchByCategoriesPresenter searchByCategoriesPresenter;
    private static FragmentManager fragmentManager;
    private static ResultFromSearchFragment resultFromSearchFragment;

    private static FragmentTransaction fragmentTransaction;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categories=new ArrayList<>();
        myAdapter=new MyAdapter(getContext(),categories);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search_by_categories, container, false);
        recyclerViewShowCategories=(RecyclerView)view.findViewById(R.id.recyclerViewCategories);
        editTextSearchByCategories=view.findViewById(R.id.editTextSearchByCategories);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager=getActivity().getSupportFragmentManager();
        searchByCategoriesPresenter=new SearchByCategoriesPresenter
                (this,
                        Repository.getInstance(RetrofitClient.getInstance(),getContext())
                );
        searchByCategoriesPresenter.getCategories(sendUrl());
        recyclerViewShowCategories.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewShowCategories.setAdapter(myAdapter);
    }

    @Override
    public void showCategories(ArrayList<Meal> categories) {
        myAdapter.setList(categories);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void failedToShowCategories(String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String sendUrl() {
        url="list.php?c=list";
        return url;
    }
    static void getMealsOfSelectedItem(Meal meal)
    {
        Bundle bundle = new Bundle();
        bundle.putString("url","filter.php?c="+meal.getStrCategory());
        resultFromSearchFragment=new ResultFromSearchFragment();
        resultFromSearchFragment.setArguments(bundle);
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView,resultFromSearchFragment);
        fragmentTransaction.addToBackStack("searchByCategoriesFragment");
//        SearchByCategoriesFragment.fragmentManager.popBackStack();
        fragmentTransaction.commit();
    }

}