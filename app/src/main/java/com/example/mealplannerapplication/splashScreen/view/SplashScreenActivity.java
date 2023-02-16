package com.example.mealplannerapplication.splashScreen.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mealplannerapplication.MainActivity;
import com.example.mealplannerapplication.R;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView splashScreen,logo,appName;
    private LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashScreen=findViewById(R.id.background);
        appName=findViewById(R.id.appName);
        lottieAnimationView=findViewById(R.id.lottie);

    }

    @Override
    protected void onResume() {
        super.onResume();
        splashScreen.animate().translationY(-3000).setDuration(1000).setStartDelay(4000);
        appName.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(2000).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}