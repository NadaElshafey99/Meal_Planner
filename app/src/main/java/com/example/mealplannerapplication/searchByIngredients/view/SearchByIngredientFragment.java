package com.example.mealplannerapplication.searchByIngredients.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.SearchByGroupActivity;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.network.RetrofitClient;
import com.example.mealplannerapplication.resultFromSearchView.view.ResultFromSearchFragment;
import com.example.mealplannerapplication.searchByIngredients.presenter.SearchByIngredientsPresenter;
import com.google.android.material.internal.TextWatcherAdapter;

import java.util.ArrayList;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;

public class SearchByIngredientFragment extends Fragment implements SearchByIngredientsViewInterface, Communicator {

    public static final String FRAGMENT_NAME = "FRAGMENT_NAME";
    private static ChosenIngredientsFragment chosenIngredientsFragment;
    private static AllIngredientsFragment allIngredientsFragment;
    private static FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;
    private static ResultFromSearchFragment resultFromSearchFragment;
    boolean emptyList = true;
    private String url;
    private SearchByIngredientsPresenter searchByIngredientsPresenter;
    private ImageView backBtn;
    private Button searchBtn;
    private EditText editTextSearchByIngredients;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            allIngredientsFragment = (AllIngredientsFragment) getActivity().getSupportFragmentManager().getFragment(savedInstanceState, "saveFragment");
        } else {
            allIngredientsFragment = new AllIngredientsFragment(this);
        }
        chosenIngredientsFragment = new ChosenIngredientsFragment();
        searchByIngredientsPresenter = new SearchByIngredientsPresenter
                (this,
                        Repository.getInstance(RetrofitClient.getInstance(), getContext()));
        searchByIngredientsPresenter.getIngredients(sendUrl());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_by_ingredient, container, false);
        searchBtn = view.findViewById(R.id.search_btn);
        backBtn = view.findViewById(R.id.back_arrow);
        editTextSearchByIngredients = view.findViewById(R.id.editTextSearchByIngredients);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's instance
        getActivity().getSupportFragmentManager().putFragment(outState, "saveFragment", allIngredientsFragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (!allIngredientsFragment.isAdded()) {
            fragmentTransaction.add(R.id.ingredientsRecyclerView, allIngredientsFragment, "saveFragment");
        }
        if (!chosenIngredientsFragment.isAdded()) {
            fragmentTransaction.add(R.id.selectedIngredientsRecyclerView, chosenIngredientsFragment);
        }
        fragmentTransaction.commit();


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!emptyList) {
                    getMealsOfSelectedItems((ArrayList<Meal>) AdapterForChosenIngredients.ingredientsList);
                } else {
                    Toast.makeText(getContext(), getString(R.string.noIngredientsAdded), Toast.LENGTH_SHORT).show();
                }
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });

        Observable<CharSequence> observable = Observable.create(i -> {
            @SuppressLint("RestrictedApi")
            TextWatcher textWatcher = new TextWatcherAdapter() {
                @Override
                public void onTextChanged(@NonNull CharSequence s, int start, int before, int count) {
                    i.onNext(s);
                }
            };
            editTextSearchByIngredients.addTextChangedListener(textWatcher);
            i.setCancellable(() -> editTextSearchByIngredients.removeTextChangedListener(textWatcher));
        });
        allIngredientsFragment.filterList(observable);
    }

    @Override
    public void addIngredients(Meal item) {
        emptyList = false;
        searchBtn.setBackgroundColor(Color.BLACK);
        chosenIngredientsFragment.updateRecyclerView(item);
    }

    @Override
    public void showIngredients(ArrayList<Meal> ingredients) {
        allIngredientsFragment.showIngredients(ingredients);
    }

    @Override
    public void failedToShowIngredients(String errMsg) {
        allIngredientsFragment.failedToShowIngredients(errMsg);
    }

    @Override
    public String sendUrl() {
        url = "list.php?i=list";
        return url;
    }

    void getMealsOfSelectedItems(ArrayList<Meal> meal) {
        if (meal != null) {
            Bundle bundle = new Bundle();
            bundle.putString("url", "filter.php?i=" + meal.get(0).getStrIngredient());
            resultFromSearchFragment = new ResultFromSearchFragment();
            resultFromSearchFragment.setArguments(bundle);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, resultFromSearchFragment);
            fragmentTransaction.commit();
        }

    }
}