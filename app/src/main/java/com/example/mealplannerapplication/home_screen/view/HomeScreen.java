package com.example.mealplannerapplication.home_screen.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplannerapplication.R;

import com.example.mealplannerapplication.db.ConcreteLocalSource;
import com.example.mealplannerapplication.home_screen.presenter.HomeScreenPresenter;
import com.example.mealplannerapplication.home_screen.presenter.HomeScreenPresenterInterface;
import com.example.mealplannerapplication.login_screen.presenter.LoginPresenter;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;

import com.example.mealplannerapplication.network.RetrofitClient;

import java.util.ArrayList;

public class HomeScreen extends Fragment implements HomeScreenViewInterface,OnMealClickListener {

    public static Resources resource;
    public static String pck;
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
        resource=getResources();
        pck= getActivity().getPackageName();
        presenterInterface = new HomeScreenPresenter(this,
                Repository.getInstance(RetrofitClient.getInstance(),
                        ConcreteLocalSource.getInstance(getContext()),getContext()));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initLayouts();
        initUI(view);
        initAdapter();
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences= HomeScreen.this.getActivity().getSharedPreferences(LoginPresenter.SHRED_PREFERENCE_FILE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                getActivity().finish();

            }
        });
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
    public void handleFavBookmark(Meal meal) {
        presenterInterface.handleFavMeal(meal);
    }

    @Override
    public void handlePlanBtn(Meal meal) {
        presenterInterface.handlePlanMeal(meal);
    }

    @Override
    public void onFavClicked(Meal meal) {
            handleFavBookmark(meal);
    }

    @Override
    public void onPlanClicked(Meal meal) {
        handlePlanBtn(meal);
    }

    private void initUI(View view) {
        myDailyRec = view.findViewById(R.id.daily_rec);
        beefRec = view.findViewById(R.id.beef_rec);
        breakfastRec = view.findViewById(R.id.breakfast_rec);
        chickenRec = view.findViewById(R.id.chicken_rec);
        desertRec = view.findViewById(R.id.desert_rec);
        logoutBtn=view.findViewById(R.id.logoutBtn);
        myDailyRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        beefRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        breakfastRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        chickenRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        desertRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }
//ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//    categoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

//    private void initLayouts() {
//        dailyLayout = new LinearLayoutManager(this.getContext());
//        dailyLayout.setOrientation(RecyclerView.HORIZONTAL);
//
//        beefLayout = new LinearLayoutManager(this.getContext());
//        beefLayout.setOrientation(RecyclerView.HORIZONTAL);
//
//        beefLayout = new LinearLayoutManager(this.getContext());
//        beefLayout.setOrientation(RecyclerView.HORIZONTAL);
//
//        breakfastLayout = new LinearLayoutManager(this.getContext());
//        breakfastLayout.setOrientation(RecyclerView.HORIZONTAL);
//
//        chickenLayout = new LinearLayoutManager(this.getContext());
//        chickenLayout.setOrientation(RecyclerView.HORIZONTAL);
//
//        desertLayout = new LinearLayoutManager(this.getContext());
//        desertLayout.setOrientation(RecyclerView.HORIZONTAL);
//
//    }

    private void initAdapter() {
        dailyAdapter = new DailyAdapter(requireContext(), new ArrayList<>(),this);
        beefAdapter = new DailyAdapter(requireContext(), new ArrayList<>(),this);
        breakfastAdapter = new DailyAdapter(requireContext(), new ArrayList<>(),this);
        chickenAdapter = new DailyAdapter(requireContext(), new ArrayList<>(),this);
        desertAdapter = new DailyAdapter(requireContext(), new ArrayList<>(),this);

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