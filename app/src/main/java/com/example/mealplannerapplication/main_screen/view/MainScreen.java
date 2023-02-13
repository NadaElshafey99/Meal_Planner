package com.example.mealplannerapplication.main_screen.view;

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
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealplannerapplication.NavigationActivity;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.home_screen.view.HomeScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;


public class MainScreen extends Fragment {
    private TextView forgotten;
    Button mySkip;
    private Button signInButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private TextInputLayout email;
    private TextInputLayout password;
    private Editable userEmail;
    private Editable userPassword;
    private SharedPreferences sharedPreferences;
    public static final String SHRED_PREFERENCE_FILE="LoginFile";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PASSWORD="password";
    public MainScreen() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

                sharedPreferences= MainScreen.this.getActivity().getSharedPreferences(SHRED_PREFERENCE_FILE,Context.MODE_PRIVATE);
                String userName=sharedPreferences.getString(KEY_EMAIL,null);
                String password=sharedPreferences.getString(KEY_PASSWORD,null);
                if(userName!=null && password!=null)
                {
                    Intent intent=new Intent(getActivity(),  NavigationActivity.class);
                    startActivity(intent);
                }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        mySkip = view.findViewById(R.id.skipBtn);
        forgotten = view.findViewById(R.id.forgetText);
        signInButton=view.findViewById(R.id.singInBtn);
        email=view.findViewById(R.id.emailLayout);
        password=view.findViewById(R.id.passwordLayout);
        userEmail=email.getEditText().getText();
        userPassword=password.getEditText().getText();
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(userEmail)) {
                    email.setError(getString(R.string.pleaseEnterEmail));
                } else {
                    email.setError(null);
                }

                if (TextUtils.isEmpty(userPassword)) {
                    password.setError(getString(R.string.pleaseEnterEmail));
                } else {
                    password.setError(null);
                }
                if(!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userPassword))
                {
                    loginUser(userEmail.toString(),userPassword.toString());
                }
            }
        });
        mySkip.setOnClickListener(view12 -> {
            Intent myIntent = new Intent(getActivity(), NavigationActivity.class);
            startActivity(myIntent);
        });
        forgotten.setOnClickListener(view1 -> System.out.println("it works!"));
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

    private void loginUser(String userEmail, String userPassword) {
        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(MainScreen.this.getActivity(),new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainScreen.this.getActivity(), "Successful", Toast.LENGTH_SHORT).show();
                            //Get instance of current user
                            firebaseUser=firebaseAuth.getCurrentUser();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_EMAIL,userEmail);
                            editor.putString(KEY_PASSWORD,userPassword);
                            editor.commit();
                            Intent intent=new Intent(getActivity(),  NavigationActivity.class);
                            startActivity(intent);

                        }
                        else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                email.setError(getString(R.string.emailNotExist));
                            }
                            catch (FirebaseAuthInvalidCredentialsException e) {
                                email.setError(getString(R.string.invalidCredentials));
                                password.setError(getString(R.string.invalidCredentials));
                            }
                            catch (Exception e) {
                                Toast.makeText(MainScreen.this.getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }

                    }
                });
    }
}