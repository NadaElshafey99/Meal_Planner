package com.example.mealplannerapplication.searchBar.view;

import static com.example.mealplannerapplication.search.view.SearchFragment.FRAGMENT_NAME;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.SearchByGroupActivity;
import com.example.mealplannerapplication.meal_details.view.MealDetailsView;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.search.view.SearchFragment;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private Context context;
    Meal result;
    private List<Meal> filteredList;
    private List<Meal> savedCategoryList=new ArrayList<>();
    private MealDetailsView mealDetailsView;
    private LayoutInflater inflater;
    private Meal clickedDataItem;
    public MyAdapter(Context context, List<Meal> filteredList){
        this.context=context;
        this.filteredList=filteredList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.design_item_for_search_bar,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        result=filteredList.get(position);
        holder.mealResultName.setText(result.getStrMeal());
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void setList(List<Meal> updatedCategories){
            this.filteredList =  updatedCategories;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mealResultName;
        ImageView categoryImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealResultName=itemView.findViewById(R.id.tv_result);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        clickedDataItem = filteredList.get(pos);
                        Intent intent = new Intent(context, SearchByGroupActivity.class);
                        intent.putExtra(SearchFragment.FRAGMENT_NAME, "mealDetailsFragment");
                        intent.putExtra("IdMeal", clickedDataItem.getIdMeal());
                        mealDetailsView=new MealDetailsView();
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
    }