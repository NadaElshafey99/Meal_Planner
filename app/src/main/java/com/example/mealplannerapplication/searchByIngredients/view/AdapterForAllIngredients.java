package com.example.mealplannerapplication.searchByIngredients.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Categories;
import com.example.mealplannerapplication.model.Ingredients;
import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class AdapterForAllIngredients extends RecyclerView.Adapter<AdapterForAllIngredients.MyViewHolder>{

    Context context;
    Meal ingredient;
    Communicator communicator;
    protected static ArrayList<? extends Parcelable>  ingredientsList;
    private LayoutInflater inflater;

    public AdapterForAllIngredients(Context context,ArrayList<? extends Parcelable> ingredientsList ,Communicator communicator){
        this.context=context;
        this.ingredientsList=ingredientsList;
        this.communicator=communicator;
    }
    public void setList(List<?> updatedCategories){
        this.ingredientsList = (ArrayList<? extends Parcelable>) updatedCategories;

    }
    @NonNull
    @Override
    public AdapterForAllIngredients.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.design_item_for_search_by_ingrdients,parent,false);
        AdapterForAllIngredients.MyViewHolder myViewHolder=new AdapterForAllIngredients.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForAllIngredients.MyViewHolder holder, int position) {
        ingredient= (Meal) ingredientsList.get(position);
        holder.ingredientName.setText(ingredient.getStrIngredient());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.getStrIngredient()+".png")
                .placeholder(new ColorDrawable(Color.TRANSPARENT))
                .into(holder.ingredientImage);
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        ImageView ingredientImage;
        View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            ingredientImage=itemView.findViewById(R.id.ingredients_image);
            ingredientName=itemView.findViewById(R.id.tv_ingredient);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        Meal clickedDataItem = (Meal) ingredientsList.get(pos);
                        communicator.addIngredients(clickedDataItem);

                    }
                }
            });

        }
    }
}