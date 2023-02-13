package com.example.mealplannerapplication.network;


import java.util.ArrayList;

public interface NetworkInterface {
     void onSuccess(ArrayList<?> list);
      void onFailure(String errMsg);
}
