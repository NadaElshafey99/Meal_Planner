package com.example.mealplannerapplication.search.view;

import android.content.Context;
import android.content.Intent;
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
import com.example.mealplannerapplication.SearchByGroupActivity;
import com.example.mealplannerapplication.model.Ingredients;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    Ingredients ingredient;
    private String fragment;
    private List<Ingredients> ingredientsList;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<Ingredients> ingredientsList,String fragment){
        this.context=context;
        this.ingredientsList=ingredientsList;
        this.fragment=fragment;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.design_item_for_search_by_categories,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ingredient=new Ingredients();
        ingredient=ingredientsList.get(position);
        holder.ingredientName.setText(ingredient.getStrIngredient());
        Glide.with(context).load(ingredient.getImage())
                .placeholder(new ColorDrawable(Color.TRANSPARENT))
                .into(holder.ingredientImage);

    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView ingredientName;
        ImageView ingredientImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage=itemView.findViewById(R.id.categories_image);
            ingredientName=itemView.findViewById(R.id.tv_categories);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(fragment.equals("categories"))
                    {
                        Intent intent = new Intent(context, SearchByGroupActivity.class);
                        intent.putExtra(SearchFragment.FRAGMENT_NAME, "searchByCategoriesFragment");
                        context.startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(context, SearchByGroupActivity.class);
                        intent.putExtra(SearchFragment.FRAGMENT_NAME, "searchByCountriesFragment");
                        context.startActivity(intent);
                    }
                }
            });

        }
    }
}