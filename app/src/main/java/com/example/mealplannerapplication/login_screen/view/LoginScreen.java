package com.example.mealplannerapplication.login_screen.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealplannerapplication.NavigationActivity;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.db.ConcreteFirebaseDB;
import com.example.mealplannerapplication.login_screen.presenter.LoginPresenter;
import com.example.mealplannerapplication.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;


public class LoginScreen extends Fragment implements LoginScreenInterface {
    Button mySkip;
    SharedPreferences sharedPreferences;
    private TextView forgotten;
    private Button signInButton;
    private TextInputLayout email;
    private TextInputLayout password;
    private Editable userEmail;
    private Editable userPassword;
    private LoginPresenter loginPresenter;
    private User user;
    private ConcreteFirebaseDB concreteFirebaseDB;

    public LoginScreen() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences = this.getActivity().getSharedPreferences(LoginPresenter.SHRED_PREFERENCE_FILE, Context.MODE_PRIVATE);
        loginPresenter.userToStillLogin(sharedPreferences);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        concreteFirebaseDB=new ConcreteFirebaseDB(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        mySkip = view.findViewById(R.id.skipBtn);
        forgotten = view.findViewById(R.id.forgetText);
        signInButton = view.findViewById(R.id.singInBtn);
        email = view.findViewById(R.id.emailLayout);
        password = view.findViewById(R.id.passwordLayout);
        userEmail = email.getEditText().getText();
        userPassword = password.getEditText().getText();
        loginPresenter = new LoginPresenter(this,concreteFirebaseDB);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(userEmail)) {
                    email.setError(getString(R.string.pleaseEnterEmail));
                } else {
                    email.setError(null);
                }

                if (TextUtils.isEmpty(userPassword)) {
                    password.setError(getString(R.string.pleaseEnterPassword));
                } else {
                    password.setError(null);
                }
                if (!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword)) {
                    user = new User(userEmail.toString(), userPassword.toString());
                    loginPresenter.checkUser(user);
                }
            }
        });
        mySkip.setOnClickListener(view12 -> {
            Intent myIntent = new Intent(getActivity(), NavigationActivity.class);
            startActivity(myIntent);
        });
        forgotten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainScreen_to_forgetPasswordFragment2);

            }
        });
        SpannableString ss = new SpannableString(getString(R.string.HaveAnAccountQuestion));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Navigation.findNavController(view).navigate(R.id.action_mainScreen_to_signupScreen);
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

    @Override
    public void onSuccessCheckUser() {
        loginPresenter.getDataWeeklyMealsFromFirebase();
        Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), NavigationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailureCheckUser() {
        Toast.makeText(getActivity(), getString(R.string.somethingWentWrong), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void alreadyLogin() {
        Intent intent = new Intent(getActivity(), NavigationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailureAuthInvalidUser() {
        email.setError(getString(R.string.emailNotExist));
    }

    @Override
    public void onFailureAuthInvalidCredentials() {
        email.setError(getString(R.string.invalidCredentials));
        password.setError(getString(R.string.invalidCredentials));

    }


}