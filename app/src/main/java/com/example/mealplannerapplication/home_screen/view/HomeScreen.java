package com.example.mealplannerapplication.home_screen.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplannerapplication.R;
import com.example.mealplannerapplication.login_screen.presenter.LoginPresenter;
import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.network.RetrofitClient;

import java.util.ArrayList;

public class HomeScreen extends Fragment implements NetworkInterface {

    RecyclerView myDailyRec;
    LinearLayoutManager layoutManager;
    DailyAdapter dailyAdapter;
    private Button logoutBtn;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        myDailyRec = view.findViewById(R.id.daily_rec);
        logoutBtn=view.findViewById(R.id.logoutBtn);
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myDailyRec.setLayoutManager(layoutManager);
        RetrofitClient retroFitClient = new RetrofitClient(myDailyRec,getContext());
        retroFitClient.startCall(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences= HomeScreen.this.getActivity().getSharedPreferences(LoginPresenter.SHRED_PREFERENCE_FILE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                getActivity().finish();

            }
        });
    }


    @Override
    public void onSuccess(ArrayList<?> list) {
        dailyAdapter = new DailyAdapter(this.getContext(),list);
        myDailyRec.setAdapter(dailyAdapter);
    }

    @Override
    public void onFailure(String errMsg) {

    }
}

/*
private FirebaseAuth firebaseAuth;
private FirebaseUser firebaseUser;
firebaseAuth= FirebaseAuth.getInstance();
firebaseUser= firebaseAuth.getCurrent();
if(firebaseUser==null)
{
  Toast.makeText(HomeScreen.this.getActivity(), "Failed", Toast.LENGTH_SHORT).show();
}
else
{
 String userID= firebaseUser.getUid();
 DatabaseReference databaseReference =FirebaseDatabase.getInstance().getReference("Registered Users");
 databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener(){
 public void onDataChanged()
 {
 UserDetails userDetails=snapshot.getValue(UserDetails.class);
     if(userDetails!=null)
     {

     }
 }
  public void onCancelled()
 {
   Toast.makeText(HomeScreen.this.getActivity(), "Failed", Toast.LENGTH_SHORT).show();
 }

 });

}
 */