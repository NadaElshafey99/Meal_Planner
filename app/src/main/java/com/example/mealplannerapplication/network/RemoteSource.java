package com.example.mealplannerapplication.network;

public interface RemoteSource {
    void enqueueCall(NetworkInterface networkInterface);
    void getUrl(String url);
}
