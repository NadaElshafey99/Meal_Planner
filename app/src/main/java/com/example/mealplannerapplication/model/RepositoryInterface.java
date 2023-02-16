package com.example.mealplannerapplication.model;

import com.example.mealplannerapplication.network.NetworkInterface;
import io.reactivex.rxjava3.core.Observable;

public interface RepositoryInterface {
    void getData(NetworkInterface networkInterfaceRef);
    Observable <Root> getMealByCategory(String category);
    void getUrl(String url);

}
