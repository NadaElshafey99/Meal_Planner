package com.example.mealplannerapplication.meal_plan.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.SearchByGroupActivity;
import com.example.mealplannerapplication.meal_details.view.MealDetailsView;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.resultFromSearchView.view.ResultFromSearchFragment;
import com.example.mealplannerapplication.search.view.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MyBreakfastAdapter extends RecyclerView.Adapter<MyBreakfastAdapter.MyViewHolder>{

    Context context;
    Meal meal;
    protected List<Meal> mealList;
    private LayoutInflater inflater;

    public MyBreakfastAdapter(Context context, List<Meal> mealList){
        this.context=context;
        this.mealList= mealList;
    }
    @NonNull
    @Override
    public MyBreakfastAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.meal_card,parent,false);
        MyBreakfastAdapter.MyViewHolder myViewHolder=new MyBreakfastAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyBreakfastAdapter.MyViewHolder holder, int position) {
        meal=mealList.get(position);
        holder.mealName.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb())
                .placeholder(new ColorDrawable(Color.TRANSPARENT))
                .into(holder.mealImage);
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public void setList(ArrayList<Meal> meals) {
        this.mealList=meals;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView mealName;
        ImageView mealImage;
        Button showDetails;
        ImageView bookmark;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName=itemView.findViewById(R.id.meal_name);
            mealImage=itemView.findViewById(R.id.meal_img);
            showDetails = itemView.findViewById(R.id.addBtn);
            bookmark = itemView.findViewById(R.id.bookmark_meal);

            showDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    // check if item still exists
                    if (pos != RecyclerView.NO_POSITION) {
                        meal = mealList.get(pos);
                        Intent intent = new Intent(context, SearchByGroupActivity.class);
                        intent.putExtra(SearchFragment.FRAGMENT_NAME, "mealDetailsFragment");
                        intent.putExtra("IdMeal", meal.getIdMeal());
                        context.startActivity(intent);
                    }
                }
            });
        }

    }

}