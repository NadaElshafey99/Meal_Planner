package com.example.mealplannerapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

//import com.example.mealplannerapplication.searchByCategories.view.SearchByCategoriesActivity;


public class MainActivity extends AppCompatActivity {
 Button btn;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btn=findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, SearchByIngredientActivity.class));
//            }
//        });
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        navController = navHostFragment.getNavController();
        //NavigationUI.setupActionBarWithNavController(this,navController);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return navController.navigateUp() || super.onOptionsItemSelected(item);
        }
        return true;
    }
}