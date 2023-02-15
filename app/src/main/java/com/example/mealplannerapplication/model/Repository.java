package com.example.mealplannerapplication.model;

import android.content.Context;

import com.example.mealplannerapplication.network.NetworkInterface;
import com.example.mealplannerapplication.network.RemoteSource;

public class Repository implements RepositoryInterface {

    RemoteSource remoteSource;

    private static Repository repo = null;

    public static Repository getInstance(RemoteSource remoteSource) {
        if (repo == null) {
            repo = new Repository(remoteSource);
        }
        return repo;
    }

    private Repository(RemoteSource remoteSource) {
        this.remoteSource = remoteSource;

    }

    @Override
    public void getDailyMeal(NetworkInterface networkInterfaceRef) {
        remoteSource.getRandomMeal(networkInterfaceRef);
    }
}
