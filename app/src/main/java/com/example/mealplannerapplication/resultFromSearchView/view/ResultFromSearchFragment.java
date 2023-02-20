package com.example.mealplannerapplication.resultFromSearchView.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.mealplannerapplication.HasNetworkConnection;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.home_screen.view.OnMealClickListener;
import com.example.mealplannerapplication.meal_details.view.MealDetailsView;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.network.RetrofitClient;
import com.example.mealplannerapplication.resultFromSearchView.presenter.ResultFromSearchPresenter;
import com.google.android.material.internal.TextWatcherAdapter;

import java.util.ArrayList;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;

public class ResultFromSearchFragment extends Fragment implements ResultFromSearchViewInterface {

    private static MealDetailsView mealDetailsView;
    private static FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;
    RecyclerView resultRecyclerView;
    MyAdapter myAdapter;
    GridLayoutManager gridLayoutManager;
    ArrayList<Meal> meals;
    ArrayList<Meal> filteredList;
    private String url;
    private ImageView backBtn;
    private ResultFromSearchPresenter resultFromSearchPresenter;
    private EditText searchMealEditText;

    static void getMealsOfSelectedItem(Meal meal) {
        Bundle bundle = new Bundle();
        bundle.putString("IdMeal", meal.getIdMeal());
        mealDetailsView = new MealDetailsView();
        mealDetailsView.setArguments(bundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, mealDetailsView);
        fragmentTransaction.addToBackStack("resultFragment");
//        SearchByCategoriesFragment.fragmentManager.popBackStack();
        fragmentTransaction.commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        meals = new ArrayList<>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            url = bundle.getString("url");
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result_from_search, container, false);
        resultRecyclerView = view.findViewById(R.id.resultRecyclerView);
        backBtn = view.findViewById(R.id.back_arrow);
        searchMealEditText = view.findViewById(R.id.searchMealEditText);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        resultRecyclerView.setLayoutManager(gridLayoutManager);
        myAdapter = new MyAdapter(getContext(), meals);
        resultRecyclerView.setAdapter(myAdapter);
        resultFromSearchPresenter = new ResultFromSearchPresenter
                (this,
                        Repository.getInstance(RetrofitClient.getInstance(), getContext())
                );
        if (HasNetworkConnection.getInstance(getContext()).isOnline()) {

            resultFromSearchPresenter.getResultMeals(sendUrl());

        } else {

            Toast.makeText(getContext(), getString(R.string.pleaseCheckYourConnection), Toast.LENGTH_SHORT).show();
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
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
            searchMealEditText.addTextChangedListener(textWatcher);
            i.setCancellable(() -> searchMealEditText.removeTextChangedListener(textWatcher));
        });
        observable.subscribe(c -> {
            filteredList = new ArrayList<>(meals
                    .stream()
                    .filter(i -> i.getStrMeal().toLowerCase().startsWith(c.toString()))
                    .collect(Collectors.toList()));
            myAdapter.setList(filteredList);
            myAdapter.notifyDataSetChanged();

        });
    }

    @Override
    public void showMealsResult(ArrayList<Meal> resultMeal) {
        this.meals = resultMeal;
        myAdapter.setList(resultMeal);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void failedToShowResultsMeal(String errMsg) {
        Toast.makeText(getContext(), getString(R.string.somethingWentWrong), Toast.LENGTH_SHORT).show();

    }

    @Override
    public String sendUrl() {
        return url;
    }


}