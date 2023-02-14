package com.example.mealplannerapplication.searchByCountry.view;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.network.RetrofitClient;
import com.example.mealplannerapplication.resultFromSearchView.view.ResultFromSearchFragment;
import com.example.mealplannerapplication.searchByCountry.presenter.SearchByCountriesPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchByCountriesFragment extends Fragment implements SearchByCountriesViewInterface {
    private RecyclerView recyclerViewShowCountries;
    private List<Meal> countryMeals;
    private MyAdapter myAdapter;
    private SearchByCountriesPresenter searchByCountriesPresenter;
    private String url;
    protected static FragmentManager fragmentManager;
    private static ResultFromSearchFragment resultFromSearchFragment;

    private static FragmentTransaction fragmentTransaction;
    static Resources resource;
    static String pck;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countryMeals=new ArrayList<>();
        myAdapter=new MyAdapter(getContext(),countryMeals);
        resource=getResources();
        pck= getContext().getPackageName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search_by_countries, container, false);
        recyclerViewShowCountries=view.findViewById(R.id.recyclerViewCountries);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewShowCountries.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewShowCountries.setAdapter(myAdapter);
        fragmentManager=getActivity().getSupportFragmentManager();
        searchByCountriesPresenter=new SearchByCountriesPresenter(this,
                Repository.getInstance(RetrofitClient.getInstance(),getContext()));
        searchByCountriesPresenter.getCountries(sendUrl());
    }

    @Override
    public void showCountries(ArrayList<?> countries) {
        myAdapter.setList(countries);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void failedToShowCategories(String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String sendUrl() {
        url="list.php?a=list";
        return url;
    }
    static void getMealsOfSelectedItem(Meal meal)
    {
        Bundle bundle = new Bundle();
        bundle.putString("url","filter.php?a="+meal.getStrArea());
        resultFromSearchFragment=new ResultFromSearchFragment();
        resultFromSearchFragment.setArguments(bundle);
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView,resultFromSearchFragment);
        fragmentTransaction.addToBackStack("searchByCountriesFragment");
        fragmentTransaction.commit();
    }
}