package com.example.mealplannerapplication.searchByCountry.view;


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

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    Categories category;
    private List<Categories> categoriesList=new ArrayList<>();
    private LayoutInflater inflater;

    public MyAdapter(Context context,List<Categories> categoriesList){
        this.context=context;
        this.categoriesList=categoriesList;
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
        category=new Categories();
        category=categoriesList.get(position);
        holder.categoryName.setText(category.getStrCategory());
        Glide.with(context).load(category.getStrCategoryThumb())
                .placeholder(new ColorDrawable(Color.TRANSPARENT))
                .into(holder.categoryImage);
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView categoryName;
        ImageView categoryImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.recyclerViewCategories);
            categoryImage=itemView.findViewById(R.id.meal_image);
            categoryName=itemView.findViewById(R.id.tv_meal);

        }
    }
}