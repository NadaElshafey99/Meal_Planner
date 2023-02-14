package com.example.mealplannerapplication.model;

import android.content.Context;

import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.network.RemoteSource;

public class Repository implements RepositoryInterface{

    private Context context;
    private RemoteSource remoteSource;
    private static Repository repository = null;

    public static Repository getInstance(RemoteSource remoteSource, Context context) {
        if (repository == null) {
            repository = new Repository(remoteSource, context);
        }
        return repository;
    }
    private Repository(RemoteSource remoteSource, Context context)
    {
        this.remoteSource=remoteSource;
        this.context=context;
    }

    @Override
    public void getData(NetworkInterface networkInterface) {
        remoteSource.enqueueCall(networkInterface);
    }

    @Override
    public void getUrl(String url) {
        remoteSource.getUrl(url);
    }
}
