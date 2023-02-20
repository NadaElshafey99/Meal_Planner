package com.example.mealplannerapplication.meal_details.view;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mealplannerapplication.HasNetworkConnection;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.addMealToWeeklyPlan.view.AddMealToWeeklyPlanner;
import com.example.mealplannerapplication.meal_details.presenter.MealDetailsPresenter;
import com.example.mealplannerapplication.model.Ingredients;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Repository;
import com.example.mealplannerapplication.network.RetrofitClient;
import com.example.mealplannerapplication.resultFromSearchView.view.ResultFromSearchFragment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.*;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MealDetailsView extends Fragment implements MealDetailsViewInterface{
    private String idMeal;
    private ImageView mealImage;
    private ImageView countryMealImage;
    private Button addToPlan;
    private ImageView backBtn;
    private TextView mealName;
    private TextView mealCountry;
    private TextView mealDescription;
    private TextView mealSteps;
    private RecyclerView ingredientsRecyclerView;
    private FragmentManager fragmentManager;
    private ResultFromSearchFragment resultFromSearchFragment;
    private AddMealToWeeklyPlanner addMealToWeeklyPlanner;
    private FragmentTransaction fragmentTransaction;
    private ArrayList<String> ingredientList;
    private ArrayList<String> measureList;
    private YouTubePlayerView youTubePlayer;
    private IngredientsAdapter ingredientsAdapter;
    private MealDetailsPresenter mealDetailsPresenter;
    private String url;
    private String[] splitYoutubeUrl;
    int i;
    static Resources resource;
    public static String pck;
    boolean finishIngredients=false;
    private Boolean youtubeURLFound = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ingredientList =new ArrayList<>();
        measureList=new ArrayList<>();
        resource=getResources();
        pck= getContext().getPackageName();
        if (getArguments() != null) {
            idMeal = getArguments().getString("IdMeal");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_meal_details_view, container, false);
        mealName=view.findViewById(R.id.details_name);
        mealCountry=view.findViewById(R.id.details_country);
        youTubePlayer=(YouTubePlayerView) view.findViewById(R.id.youTubePlayerView);
        backBtn=view.findViewById(R.id.back_arrow);
        getLifecycle().addObserver(youTubePlayer);
        mealSteps=view.findViewById(R.id.details_steps);
        mealImage=view.findViewById(R.id.details_image);
        countryMealImage=view.findViewById(R.id.country_img);
        ingredientsRecyclerView=view.findViewById(R.id.detail_ingredientsRecyclerView);
        addToPlan=view.findViewById(R.id.addToPlanButton);
        ingredientsAdapter=new IngredientsAdapter(getContext(),ingredientList,measureList);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        fragmentManager=getActivity().getSupportFragmentManager();
        mealDetailsPresenter=new MealDetailsPresenter(this, Repository.getInstance(RetrofitClient.getInstance(),getContext()));

        if (HasNetworkConnection.getInstance(getContext()).isOnline()) {

            mealDetailsPresenter.getMealDetails(sendUrl());

        } else {

            Toast.makeText(getContext(), getString(R.string.pleaseCheckYourConnection), Toast.LENGTH_SHORT).show();
        }

        ingredientsRecyclerView.setAdapter(ingredientsAdapter);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }

    @Override
    public void showMealDetails(Meal meal) {
        getAllIngredients(meal);
        mealName.setText(meal.getStrMeal());
        mealCountry.setText(meal.getStrArea());
        mealSteps.setText(meal.getStrInstructions());
        String countryName=meal.getStrArea().toLowerCase();
        String countryImage="@drawable/"+countryName;
        int imageResource = resource.getIdentifier(countryImage,null,pck);
        Drawable drawable = resource.getDrawable(imageResource);
        countryMealImage.setImageDrawable(drawable);
        Glide.with(getContext()).load(meal.getStrMealThumb())
                .placeholder(new ColorDrawable(Color.TRANSPARENT))
                .into(mealImage);
        if (!meal.getStrYoutube().isEmpty()) {

            splitYoutubeUrl = meal.getStrYoutube().split("=");
            youtubeURLFound = true;
            youTubePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    if (youtubeURLFound) {
                        String videoId = splitYoutubeUrl[1];
                        youTubePlayer.cueVideo(videoId,0);}
                }
            });
        } else {
            youTubePlayer.setVisibility(View.GONE);
        }
        addToPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("IdMeal",meal.getIdMeal());
                bundle.putString("urlImage",meal.getStrMealThumb());
                bundle.putString("mealName",meal.getStrMeal());
                addMealToWeeklyPlanner=new AddMealToWeeklyPlanner();
                addMealToWeeklyPlanner.setArguments(bundle);
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView,addMealToWeeklyPlanner);
                fragmentTransaction.addToBackStack("mealDetailsFragment");
                fragmentTransaction.commit();
            }
        });

    }


    private void getAllIngredients(Meal meal) {
        finishIngredients=false;
        for(i=1;i<16&& !finishIngredients;i++)
        {
            switch(i)
            {
                case 1:
                    if(!meal.getStrIngredient1().equals("")) {
                       ingredientList.add(meal.getStrIngredient1());
                       measureList.add(meal.getStrMeasure1());
                       break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 2:
                    if(!meal.getStrIngredient2().equals("")) {
                        ingredientList.add(meal.getStrIngredient2());
                        measureList.add(meal.getStrMeasure2());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 3:
                    if(!meal.getStrIngredient3().equals("")) {
                        ingredientList.add(meal.getStrIngredient3());
                        measureList.add(meal.getStrMeasure3());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 4:
                    if(!meal.getStrIngredient4().equals("")) {
                        ingredientList.add(meal.getStrIngredient4());
                        measureList.add(meal.getStrMeasure4());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 5:
                    if(!meal.getStrIngredient5().equals("")) {
                        ingredientList.add(meal.getStrIngredient5());
                        measureList.add(meal.getStrMeasure5());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 6:
                    if(!meal.getStrIngredient6().equals("")) {
                        ingredientList.add(meal.getStrIngredient6());
                        measureList.add(meal.getStrMeasure6());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 7:
                    if(!meal.getStrIngredient7().equals("")) {
                        ingredientList.add(meal.getStrIngredient7());
                        measureList.add(meal.getStrMeasure7());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 8:
                    if(!meal.getStrIngredient8().equals("")) {
                        ingredientList.add(meal.getStrIngredient8());
                        measureList.add(meal.getStrMeasure8());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 9:
                    if(!meal.getStrIngredient9().equals("")) {
                        ingredientList.add(meal.getStrIngredient9());
                        measureList.add(meal.getStrMeasure9());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 10:
                    if(!meal.getStrIngredient10().equals("")) {
                        ingredientList.add(meal.getStrIngredient10());
                        measureList.add(meal.getStrMeasure10());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;
                case 11:
                    if(!meal.getStrIngredient11().equals("")) {
                        ingredientList.add(meal.getStrIngredient11());
                        measureList.add(meal.getStrMeasure11());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 12:
                    if(!meal.getStrIngredient12().equals("")) {
                        ingredientList.add(meal.getStrIngredient12());
                        measureList.add(meal.getStrMeasure12());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 13:
                    if(!meal.getStrIngredient13().equals("")) {
                        ingredientList.add(meal.getStrIngredient13());
                        measureList.add(meal.getStrMeasure13());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 14:
                    if(!meal.getStrIngredient14().equals("")) {
                        ingredientList.add(meal.getStrIngredient14());
                        measureList.add(meal.getStrMeasure14());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                case 15:
                    if(!meal.getStrIngredient15().equals("")) {
                        ingredientList.add(meal.getStrIngredient15());
                        measureList.add(meal.getStrMeasure15());
                        break;
                    }
                    else {
                        finishIngredients=true;
                    }
                    break;

                default:
                    finishIngredients=true;
                    break;
            }
        }
        ingredientsAdapter.setList(ingredientList,measureList);
        ingredientsAdapter.notifyDataSetChanged();
    }

    @Override
    public void failedToShowMealDetails(String errMsg) {
        Toast.makeText(getContext(), getString(R.string.somethingWentWrong), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String sendUrl() {
        url="lookup.php?i="+idMeal;
        return url;
    }
}