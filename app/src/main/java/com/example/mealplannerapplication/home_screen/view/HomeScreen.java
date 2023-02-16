package com.example.mealplannerapplication.home_screen.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplannerapplication.R;

import com.example.mealplannerapplication.home_screen.presenter.HomeScreenPresenter;
import com.example.mealplannerapplication.home_screen.presenter.HomeScreenPresenterInterface;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;

import com.example.mealplannerapplication.network.RetrofitClient;

import java.util.ArrayList;

public class HomeScreen extends Fragment implements HomeScreenViewInterface {

    RecyclerView myDailyRec;
    RecyclerView beefRec;
    RecyclerView breakfastRec;
    RecyclerView chickenRec;
    RecyclerView desertRec;
    LinearLayoutManager dailyLayout;
    LinearLayoutManager beefLayout;
    LinearLayoutManager breakfastLayout;
    LinearLayoutManager chickenLayout;
    LinearLayoutManager desertLayout;
    DailyAdapter dailyAdapter;
    private Button logoutBtn;
    SharedPreferences sharedPreferences;

    DailyAdapter beefAdapter;
    DailyAdapter breakfastAdapter;
    DailyAdapter chickenAdapter;
    DailyAdapter desertAdapter;
    HomeScreenPresenterInterface presenterInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayouts();
        presenterInterface = new HomeScreenPresenter(this, Repository.getInstance(RetrofitClient.getInstance(),getContext()));
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        initUI(view);
        initAdapter();
        return view;
    }

    @Override
    public void showDailyMeals(ArrayList<Meal> meals) {
        dailyAdapter.setDailyList(meals);
        dailyAdapter.notifyDataSetChanged();

        myDailyRec.setAdapter(dailyAdapter);
    }

    @Override
    public void showBeefCategory(ArrayList<Meal> meals) {
        beefAdapter.setDailyList(meals);
        beefAdapter.notifyDataSetChanged();
        beefRec.setAdapter(beefAdapter);
    }

    @Override
    public void breakfast(ArrayList<Meal> meals) {
        breakfastAdapter.setDailyList(meals);
        breakfastAdapter.notifyDataSetChanged();
        breakfastRec.setAdapter(breakfastAdapter);
    }

    @Override
    public void showChickenCategory(ArrayList<Meal> meals) {
        chickenAdapter.setDailyList(meals);
        chickenAdapter.notifyDataSetChanged();
        chickenRec.setAdapter(chickenAdapter);
    }

    @Override
    public void showDesertCategory(ArrayList<Meal> meals) {
        desertAdapter.setDailyList(meals);
        desertAdapter.notifyDataSetChanged();
        desertRec.setAdapter(desertAdapter);
    }

    @Override
    public void addMeal(Meal meal) {

    }

    private void initUI(View view) {
        myDailyRec = view.findViewById(R.id.daily_rec);
        beefRec = view.findViewById(R.id.beef_rec);
        breakfastRec = view.findViewById(R.id.breakfast_rec);
        chickenRec = view.findViewById(R.id.chicken_rec);
        desertRec = view.findViewById(R.id.desert_rec);

        myDailyRec.setLayoutManager(dailyLayout);
        beefRec.setLayoutManager(beefLayout);
        breakfastRec.setLayoutManager(breakfastLayout);
        chickenRec.setLayoutManager(chickenLayout);
        desertRec.setLayoutManager(desertLayout);
    }


    private void initLayouts() {
        dailyLayout = new LinearLayoutManager(this.getContext());
        dailyLayout.setOrientation(RecyclerView.HORIZONTAL);

        beefLayout = new LinearLayoutManager(this.getContext());
        beefLayout.setOrientation(RecyclerView.HORIZONTAL);

        beefLayout = new LinearLayoutManager(this.getContext());
        beefLayout.setOrientation(RecyclerView.HORIZONTAL);

        breakfastLayout = new LinearLayoutManager(this.getContext());
        breakfastLayout.setOrientation(RecyclerView.HORIZONTAL);

        chickenLayout = new LinearLayoutManager(this.getContext());
        chickenLayout.setOrientation(RecyclerView.HORIZONTAL);

        desertLayout = new LinearLayoutManager(this.getContext());
        desertLayout.setOrientation(RecyclerView.HORIZONTAL);

    }

    private void initAdapter() {
        dailyAdapter = new DailyAdapter(requireContext(), new ArrayList<>());
        beefAdapter = new DailyAdapter(requireContext(), new ArrayList<>());
        breakfastAdapter = new DailyAdapter(requireContext(), new ArrayList<>());
        chickenAdapter = new DailyAdapter(requireContext(), new ArrayList<>());
        desertAdapter = new DailyAdapter(requireContext(), new ArrayList<>());

    }

    private void loadData(){
        presenterInterface.getDailyMeals();
        presenterInterface.getBeefCategory();
        presenterInterface.getBreakfastCategory();
        presenterInterface.getChickenCategory();
        presenterInterface.getDesertCategory();
    }
}

/*
private FirebaseAuth firebaseAuth;
private FirebaseUser firebaseUser;
firebaseAuth= FirebaseAuth.getInstance();
firebaseUser= firebaseAuth.getCurrent();
if(firebaseUser==null)
{
  Toast.makeText(HomeScreen.this.getActivity(), "Failed", Toast.LENGTH_SHORT).show();
}
else
{
 String userID= firebaseUser.getUid();
 DatabaseReference databaseReference =FirebaseDatabase.getInstance().getReference("Registered Users");
 databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener(){
 public void onDataChanged()
 {
 UserDetails userDetails=snapshot.getValue(UserDetails.class);
     if(userDetails!=null)
     {

     }
 }
  public void onCancelled()
 {
   Toast.makeText(HomeScreen.this.getActivity(), "Failed", Toast.LENGTH_SHORT).show();
 }

 });

}
 */