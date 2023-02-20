package com.example.mealplannerapplication.signup_screen.presenter;

import androidx.annotation.NonNull;

import com.example.mealplannerapplication.model.User;
import com.example.mealplannerapplication.signup_screen.view.SignupScreenInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupPresenter implements SignupPresenterInterface {

    private SignupScreenInterface signupScreenInterface;
    private User user;

    public SignupPresenter(SignupScreenInterface signupScreenInterface) {
        this.signupScreenInterface = signupScreenInterface;
        user=new User();
    }

    @Override
    public void registerUser(User user) {

        FirebaseAuth authentication = FirebaseAuth.getInstance();
        this.user=user;
        authentication.createUserWithEmailAndPassword(user.getUserEmail(), user.getUserPassword()).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) //create user
                        {
                            FirebaseUser firebaseUser = authentication.getCurrentUser();
                            //Enter data of user
                            //Extracting user reference from DB
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Registered Users");
                            //to refer to data later very asy with unique ID
                            databaseReference.child(firebaseUser.getUid()).child("Personal Information").setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) // saved/set data in DB
                                            {
                                                //send verification
                                                firebaseUser.sendEmailVerification();
                                                signupScreenInterface.onSuccessRegistered();
                                            } else {
                                                signupScreenInterface.onFailureSetData();
                                            }
                                        }
                                    });
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthUserCollisionException exception) {
                                signupScreenInterface.onFailureRegistered();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
    }

}
