package com.example.sep4_android.webService;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.HealthData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthRepository {
    private static HealthRepository instance;
    private final MutableLiveData<HealthData> randomHealthData;
    private HealthAPI healthAPI;

    public HealthRepository(){
        randomHealthData = new MutableLiveData<>();
        healthAPI = HealthServiceGenerator.getHealthAPI();
    }

    public static synchronized HealthRepository getInstance()
    {
        if (instance == null)
            instance = new HealthRepository();
        return instance;
    }

    public LiveData<HealthData> getRandomHealthData(){
        return randomHealthData;
    }

    public void searchForHealthData(String url) {
        Log.i("Retrofit", "Start (searchForHealthData) - url: " +  url);
        Call<HealthData> call = healthAPI.getRandomTemperature(url);
        call.enqueue(new Callback<HealthData>() {
            @Override
            public void onResponse(Call<HealthData> call, Response<HealthData> response) {
                Log.i("Retrofit", "Reponse: " + response);
                if (response.isSuccessful()) {
                    HealthData healthData = response.body();
                    Log.i("Retrofit", "SUCCESS!\nHealth Data: " + healthData);
                }
            }
            @Override
            public void onFailure(Call<HealthData> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (searchForHealthData)"
                        +"\nError Message: " + t.getMessage());
            }
        });
    }
}
