package com.example.mealplannerapplication.network;

public interface RemoteSource {
    //instead of enqueue
    void getRandomMeal(NetworkInterface networkInterfaceRef);
}
