package com.example.mealplannerapplication.network;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mealplannerapplication.home_screen.view.DailyAdapter;
import com.example.mealplannerapplication.model.Categories;
import com.example.mealplannerapplication.model.Ingredients;
import com.example.mealplannerapplication.model.RetrievedList;
import com.example.mealplannerapplication.model.Meal;
import com.example.mealplannerapplication.model.Root;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements RemoteSource{
    ArrayList<?> mealsList;
    ArrayList<Categories> categories;
    ArrayList<Ingredients> ingredients;
    CallsToServer callsToServer;
    private static final String RANDOM_MEAL_URL = "https://www.themealdb.com/api/json/v1/1/random.php/";
    private static final String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    RecyclerView recyclerView;
    DailyAdapter adapter;
    private String url;
    Context context;
    View view;
    private static RetrofitClient retrofitClient=null;


    public static RetrofitClient getInstance()
    {
        if(retrofitClient==null)
        {
            retrofitClient=new RetrofitClient();
        }
        return retrofitClient;
    }

    public RetrofitClient(RecyclerView rc, Context c) {
        this.context = c;
        this.recyclerView = rc;
    }
    public RetrofitClient() {

    }
//    private RetrofitClient() {
//
//    }

    public void startCall(NetworkInterface interfaceRef) {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RANDOM_MEAL_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        callsToServer = retrofit.create(CallsToServer.class);

        Call<Root>  meals = callsToServer.getRandomMeal();

        Callback<Root> mMeals = new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mealsList = response.body().getMeals();
                    interfaceRef.onSuccess(mealsList);
                }
            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                t.printStackTrace();
            }
        };
        meals.enqueue(mMeals);
    }

//
//    void startCall(int id) {
//        Gson gson = new GsonBuilder().create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(RANDOM_MEAL_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//    }

    @Override
    public void enqueueCall(NetworkInterface networkInterface) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        CallsToServer callsToServer =retrofit.create(CallsToServer.class);
        Observable<RetrievedList> call =  callsToServer.getDataFomApi(url);
        call.subscribeOn(Schedulers.io())
                .map(RetrievedList::getMeals)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->{
                            mealsList= item;
                            networkInterface.onSuccess(mealsList);
                        }

                );
        /*call.subscribeOn(Schedulers.io())
                .map(RetrievedList::getMeals)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->{
                            ingredients= item;
                            networkInterface.onSuccess(ingredients);
                        }

                );*/

        /*call.subscribeOn(Schedulers.io())
                .map(RetrievedList::getMeals)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item->{
                            categories= item;
                            networkInterface.onSuccess(categories);
                        }

                );*/
        /*call.enqueue(new Callback<CategoriesList>() {
            @Override
            public void onResponse(Call<CategoriesList> call, Response<CategoriesList> response) {
                if(response.isSuccessful()){
                    networkInterface.onSuccess(response.body().getCategoriesList());
                }
            }
            @Override
            public void onFailure(Call<CategoriesList> call, Throwable t) {
                networkInterface.onFailure(t.getMessage().toString());
                Log.e("TAG", "onFailure: "+t.getMessage() );
            }
        });*/
    }

    @Override
    public void getUrl(String url) {
        this.url=url;
    }
}
