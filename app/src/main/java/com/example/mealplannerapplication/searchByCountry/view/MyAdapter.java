package com.example.mealplannerapplication.searchByCountry.view;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Categories;
import com.example.mealplannerapplication.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    Meal country;
    private List<Meal> countriesList;
    private LayoutInflater inflater;
    private String countryImage;

    public MyAdapter(Context context,List<Meal> countriesList){
        this.context=context;
        this.countriesList=countriesList;
    }

    public void setList(List<Meal> countriesList) {
        this.countriesList = countriesList;
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
        country=countriesList.get(position);
        holder.countryName.setText(country.getStrArea());
        String countryName=country.getStrArea().toLowerCase();
        String countryImage="@drawable/"+countryName;
        int imageResource = SearchByCountriesActivity.res.getIdentifier(countryImage, null,SearchByCountriesActivity.pck);
        Drawable drawable = SearchByCountriesActivity.res.getDrawable(imageResource);
        holder.countryImage.setImageDrawable(drawable);

    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        TextView countryName;
        ImageView countryImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            countryImage=itemView.findViewById(R.id.categories_image);
            countryName=itemView.findViewById(R.id.tv_categories);

        }
    }
}