package com.example.mealplannerapplication.searchByCategories.view;


import static com.example.mealplannerapplication.searchByCategories.view.SearchByCategoriesFragment.editTextSearchByCategories;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import io.reactivex.rxjava3.core.Observable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.model.Categories;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.resultFromSearchView.view.ResultFromSearchFragment;
import com.google.android.material.internal.TextWatcherAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    Meal category;
    private List<Meal> categoriesList;
    private List<Meal> filteredList;
    private LayoutInflater inflater;
    private Meal clickedDataItem;
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
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        clickedDataItem = categoriesList.get(pos);
                        SearchByCategoriesFragment.getMealsOfSelectedItem(clickedDataItem);
                    }
                }
            });
            /*Observable<CharSequence> observable= Observable.create(i->{
                @SuppressLint("RestrictedApi")
                TextWatcher textWatcher=new TextWatcherAdapter(){
                    @Override
                    public void onTextChanged(@NonNull CharSequence s, int start, int before, int count) {
                        i.onNext(s);
                    }
                };
                editTextSearchByCategories.addTextChangedListener(textWatcher);
                i.setCancellable(()->editTextSearchByCategories.removeTextChangedListener(textWatcher));
            });

            observable.subscribe(c->{
                filteredList=new ArrayList<>(categoriesList
                        .stream()
                        .filter(i-> i.getStrCategory().startsWith(c.toString()))
                        .collect(Collectors.toList()));
                setList(filteredList);
                notifyDataSetChanged();});*/
        }

        }
    }