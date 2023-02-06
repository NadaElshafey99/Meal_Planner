package com.example.mealplannerapplication.searchByIngredients.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class AdapterForAllIngredients extends RecyclerView.Adapter<AdapterForAllIngredients.MyViewHolder>{

    Context context;
    Ingredients ingredient;
    Communicator communicator;
    private List<Ingredients> ingredientsList;
    private LayoutInflater inflater;

    public AdapterForAllIngredients(Context context,List<Ingredients> ingredientsList,Communicator communicator){
        this.context=context;
        this.ingredientsList=ingredientsList;
        this.communicator=communicator;
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
        ingredient=ingredientsList.get(position);
        holder.ingredientName.setText(ingredient.getStrIngredient());
        Glide.with(context).load(ingredient.getImage())
                .placeholder(new ColorDrawable(Color.TRANSPARENT))
                .into(holder.ingredientImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                communicator.addIngredients(ingredient);
                Toast.makeText(context,ingredient.getStrIngredient(),Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        ImageView ingredientImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage=itemView.findViewById(R.id.ingredient_image);
            ingredientName=itemView.findViewById(R.id.tv_ingredient);

        }
    }
}