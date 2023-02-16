package com.example.mealplannerapplication.home_screen.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.MyViewHolder> {

    Context myContext;
    ArrayList<Meal> dailyMeal;

    public DailyAdapter(@NonNull Context context, @Nullable ArrayList<?> meals) {
        this.myContext = context;
        this.dailyMeal = meals;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.meal_card, parent, false);
        return new MyViewHolder(view);
    }

    public void setDailyList(ArrayList<Meal> updatedMeals){
        this.dailyMeal = updatedMeals;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal currentMeal = dailyMeal.get(position);
        holder.getMealName().setText(currentMeal.getStrMeal());
        holder.getMealBtn().setOnClickListener(view -> System.out.println("meal added to plan"));
        holder.getBookmark().setOnClickListener(view -> System.out.println("meal added to favorite"));
        Glide.with(myContext).load(currentMeal.getStrMealThumb()).into(holder.getMealImg());
    }

    @Override
    public int getItemCount() {
        return dailyMeal.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final ConstraintLayout mealConstLayout;
        private final TextView mealName;
        private final Button mealBtn;
        private final ImageView mealImg;
        private final ImageView bookmark;

        public MyViewHolder(View view) {
            super(view);
            mealName = view.findViewById(R.id.meal_name);
            mealConstLayout = view.findViewById(R.id.meal_const_layout);
            mealImg = view.findViewById(R.id.meal_img);
            cardView = view.findViewById(R.id.cardView);
            mealBtn = view.findViewById(R.id.addBtn);
            bookmark = view.findViewById(R.id.bookmark_meal);
        }

        public ImageView getBookmark() {
            return bookmark;
        }

        public CardView getCardView() {
            return cardView;
        }

        public ConstraintLayout getMealConstLayout() {
            return mealConstLayout;
        }

        public TextView getMealName() {
            return mealName;
        }

        public Button getMealBtn() {
            return mealBtn;
        }

        public ImageView getMealImg() {
            return mealImg;
        }

    }
}
