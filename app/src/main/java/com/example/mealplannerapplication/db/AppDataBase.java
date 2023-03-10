package com.example.mealplannerapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealplannerapplication.model.Meal;

@Database(entities = {Meal.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "meals")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract MealDAO mealDAO();
}
