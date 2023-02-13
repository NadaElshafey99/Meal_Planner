package com.example.mealplannerapplication.forgetPassword.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mealplannerapplication.MainActivity;
import com.example.mealplannerapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordFragment extends Fragment {

    private Button resetButton;
    private TextInputEditText emailEditText;
    private String emailText;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        resetButton=view.findViewById(R.id.forgetPassword_reset_btn);
        emailEditText=view.findViewById(R.id.emailEditText);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailText=emailEditText.getText().toString();
                if(TextUtils.isEmpty(emailText))
                {
                    Toast.makeText(getActivity(), getString(R.string.pleaseEnterEmailToResetYourPassword), Toast.LENGTH_SHORT).show();
                    emailEditText.setError(getString(R.string.emailIsRequired));
                }
                else
                {
                    resetPassword(emailText);
                }
            }
        });
        return view;
    }

    private void resetPassword(String email) {
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getActivity(), getString(R.string.pleaseCheckyourInboxForPasswordResetLink), Toast.LENGTH_SHORT).show();
                            Intent myIntent = new Intent(getActivity(), MainActivity.class);
                            startActivity(myIntent);
                            getActivity().finish();

                        }
                        else
                        {
                            Toast.makeText(getActivity(), getString(R.string.somethingWentWrong), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}