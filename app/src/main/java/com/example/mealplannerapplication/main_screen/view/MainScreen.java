package com.example.mealplannerapplication.main_screen.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealplannerapplication.NavigationActivity;
import com.example.mealplannerapplication.R;


public class MainScreen extends Fragment {
    TextView forgotten;
    Button mySkip;

    public MainScreen() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        mySkip = view.findViewById(R.id.skipBtn);
        forgotten = view.findViewById(R.id.forgetText);
        mySkip.setOnClickListener(view12 -> {
            Intent myIntent = new Intent(getActivity(), NavigationActivity.class);
            startActivity(myIntent);
        });
        forgotten.setOnClickListener(view1 -> System.out.println("it works!"));

        SpannableString ss = new SpannableString("Don't have an account?SignUp");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Navigation.findNavController(view).navigate(MainScreenDirections.actionMainScreenToSignupScreen());
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, 22, 28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = (TextView) view.findViewById(R.id.testView);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.TRANSPARENT);
        return view;
    }
}