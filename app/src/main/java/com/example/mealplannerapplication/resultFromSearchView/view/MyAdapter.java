package com.example.mealplannerapplication.resultFromSearchView.view;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Categories;
import com.example.mealplannerapplication.model.Meal;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    Meal category;
    private List<Meal> categoriesList;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<Meal> categoriesList){
        this.context=context;
        this.categoriesList=categoriesList;
    }
    public void setList(List<?> updatedCategories){
        this.categoriesList = (List<Meal>) updatedCategories;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.meal_card,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        category=categoriesList.get(position);
        holder.mealName.setText(category.getStrMeal());
        Glide.with(context).load(category.getStrMealThumb())
                .placeholder(new ColorDrawable(Color.TRANSPARENT))
                .into(holder.mealImage);
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView mealName;
        ImageView mealImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage=itemView.findViewById(R.id.meal_img);
            mealName=itemView.findViewById(R.id.meal_name);

        }
    }
}