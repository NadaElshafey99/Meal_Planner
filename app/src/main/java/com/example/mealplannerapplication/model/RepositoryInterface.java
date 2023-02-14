package com.example.mealplannerapplication.model;

import com.example.mealplannerapplication.network.NetworkInterface;

public interface RepositoryInterface {
    void getData(NetworkInterface networkInterface);
    void getUrl(String url);
}
