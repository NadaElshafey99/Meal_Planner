package com.example.mealplannerapplication.meal_details.view;

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
import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;
import java.util.HashMap;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>{

    Context context;
    String ingredient;
    String measure;
    ArrayList<String> ingredientsList;
    ArrayList<String> measureList;
    private LayoutInflater inflater;

    public IngredientsAdapter(Context context, ArrayList<String> ingredientsList,ArrayList<String> measureList){
        this.context=context;
        this.ingredientsList=ingredientsList;
        this.measureList=measureList;
    }

    public void setList(ArrayList<String> ingredientsList,ArrayList<String> measureList) {
        this.ingredientsList=ingredientsList;
        this.measureList=measureList;
    }
    @NonNull
    @Override
    public IngredientsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.design_item_for_ingredient_meal_details,parent,false);
        IngredientsAdapter.MyViewHolder myViewHolder=new IngredientsAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.MyViewHolder holder, int position) {

        ingredient=ingredientsList.get(position);
        measure=measureList.get(position);
            holder.ingredientName.setText(ingredient);
            holder.tvMeasure.setText(measure);
            Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient+ ".png")
                    .placeholder(new ColorDrawable(Color.TRANSPARENT))
                    .into(holder.ingredientImage);
//
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView ingredientName;
        TextView tvMeasure;
        ImageView ingredientImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage=itemView.findViewById(R.id.ingredients_image);
            ingredientName=itemView.findViewById(R.id.tv_ingredient);
            tvMeasure=itemView.findViewById(R.id.tv_measure);

        }
    }
}