package com.example.mealplannerapplication.searchBar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.network.RetrofitClient;
import com.example.mealplannerapplication.resultFromSearchView.view.ResultFromSearchFragment;
import com.example.mealplannerapplication.searchBar.presenter.SearchBarPresenter;
import com.example.mealplannerapplication.searchBar.presenter.SearchBarPresenterInterface;
import com.google.android.material.internal.TextWatcherAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class SearchBarActionScreen extends AppCompatActivity implements SearchBarActionScreenInterface{

    private RecyclerView recyclerViewShowResults;
    private List<Meal> filteredList;
    private List<Meal> resultList;
    private MyAdapter myAdapter;
    private String url;
    protected static EditText editTextSearch;
    private static FragmentManager fragmentManager;
    private static ResultFromSearchFragment resultFromSearchFragment;
    private ImageView backBtn;
    private static FragmentTransaction fragmentTransaction;
    Observable<CharSequence> observable;
    private SearchBarPresenter searchBarPresenter;

    public static void getMealsOfSelectedItem(Meal clickedDataItem) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar_action_screen);
        recyclerViewShowResults=findViewById(R.id.recyclerViewResults);
        backBtn=findViewById(R.id.back_arrow);
        editTextSearch=findViewById(R.id.editTextSearch);
        filteredList=new ArrayList<>();
        myAdapter=new MyAdapter(this,filteredList);
        searchBarPresenter=new SearchBarPresenter(this,
                        Repository.getInstance(RetrofitClient.getInstance(),this));
    }
    @Override
    protected void onResume() {
        super.onResume();
        fragmentManager=getSupportFragmentManager();
        recyclerViewShowResults.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerViewShowResults.setAdapter(myAdapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        observable= Observable.create(i->{
            @SuppressLint("RestrictedApi")
            TextWatcher textWatcher=new TextWatcherAdapter(){
                @Override
                public void onTextChanged(@NonNull CharSequence s, int start, int before, int count) {
//                    i.onNext(s);
                    if(!s.toString().isEmpty()) {
                        searchBarPresenter.getMealsFiltered(sendUrl()+s);
                    }
                }
            };
            editTextSearch.addTextChangedListener(textWatcher);
            i.setCancellable(()->editTextSearch.removeTextChangedListener(textWatcher));
        });
        observable.subscribe(c->{
            filteredList=new ArrayList<>(resultList
                    .stream()
                    .filter(i-> i.getStrMeal().startsWith(c.toString()))
                    .collect(Collectors.toList()));});
        myAdapter.setList(filteredList);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void showResultOfSearch(ArrayList<Meal> result) {
        this.resultList=result;
        myAdapter.setList(result);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void failedToShowResult(String errMsg) {
        Toast.makeText(this, getString(R.string.somethingWentWrong), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String sendUrl() {
        url="search.php?s=";
        return url;
    }
}