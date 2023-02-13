package com.example.mealplannerapplication.searchByCategories.view;


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

    public MyAdapter(Context context,List<Meal> categoriesList){
        this.context=context;
        this.categoriesList=categoriesList;
    }
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.design_item_for_search_by_categories,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        category=categoriesList.get(position);
        holder.categoryName.setText(category.getStrCategory());
        Glide.with(context).load("https://www.themealdb.com//images//category//"+category.getStrCategory()+".png")
                .placeholder(new ColorDrawable(Color.TRANSPARENT))
                .into(holder.categoryImage);
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
        public void setList(List<?> updatedCategories){
            this.categoriesList = (List<Meal>) updatedCategories;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView categoryName;
        ImageView categoryImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.recyclerViewCategories);
            categoryImage=itemView.findViewById(R.id.categories_image);
            categoryName=itemView.findViewById(R.id.tv_categories);

        }
    }
}