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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mealplannerapplication.HasNetworkConnection;
import com.example.mealplannerapplication.NavigationActivity;
import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.db.ConcreteFirebaseDB;
import com.example.mealplannerapplication.login_screen.presenter.LoginPresenter;
import com.example.mealplannerapplication.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginScreen extends Fragment implements LoginScreenInterface {
    Button mySkip;
    SharedPreferences sharedPreferences;
    private TextView forgotten;
    private TextView googleSignIn;
    private Button signInButton;
    private TextInputLayout email;
    private ImageView google;
    private TextInputLayout password;
    private Editable userEmail;
    private Editable userPassword;
    private LoginPresenter loginPresenter;
    private User user;
    private ConcreteFirebaseDB concreteFirebaseDB;
    public static boolean isGuest=false;
    private GoogleSignInClient signInClient;

    public LoginScreen() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences = this.getActivity().getSharedPreferences(LoginPresenter.SHRED_PREFERENCE_FILE, Context.MODE_PRIVATE);
        loginPresenter.userToStillLogin(sharedPreferences);
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null)
        {
            Intent intent=new Intent(getActivity(),NavigationActivity.class);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        concreteFirebaseDB=new ConcreteFirebaseDB(getContext());
        GoogleSignInOptions options= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        signInClient= GoogleSignIn.getClient(getContext(),options);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        mySkip = view.findViewById(R.id.skipBtn);
        forgotten = view.findViewById(R.id.forgetText);
        signInButton = view.findViewById(R.id.singInBtn);
        email = view.findViewById(R.id.emailLayout);
        password = view.findViewById(R.id.passwordLayout);
        googleSignIn=view.findViewById(R.id.textgoogle);
        google=view.findViewById(R.id.google);
        loginPresenter = new LoginPresenter(this,concreteFirebaseDB);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail = email.getEditText().getText();
                userPassword = password.getEditText().getText();
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
                   
                    if (HasNetworkConnection.getInstance(getContext()).isOnline()) {

                        loginPresenter.checkUser(user);


                    } else {
                        Toast.makeText(getContext(), getString(R.string.pleaseCheckYourConnection), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=signInClient.getSignInIntent();
                startActivityForResult(intent,1234);
            }
        });

        mySkip.setOnClickListener(view12 -> {
            Intent myIntent = new Intent(getActivity(), NavigationActivity.class);
            isGuest=true;
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null)
        { if(requestCode==1234)
        {
                try {
                    Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
                    GoogleSignInAccount account=task.getResult(ApiException.class);
                    AuthCredential credential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        isGuest=false;
                                        Intent intent=new Intent(getActivity().getApplicationContext(),NavigationActivity.class);
                                        startActivity(intent);

                                    }
                                    else {
                                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    @Override
    public void onSuccessCheckUser() {
        isGuest=false;
        loginPresenter.getDataWeeklyMealsFromFirebase();
        Toast.makeText(getActivity(), getString(R.string.success), Toast.LENGTH_SHORT).show();
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