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

    public void findRandomHealthData() {
        Log.i("Retrofit", "Start (searchForHealthData) - url: ");
        Call<HealthData[]> call = healthAPI.getRandomHealthData(2);
        Log.i("Retrofit", "(searchForHealthData) - Call: " + call);
        call.enqueue(new Callback<HealthData[]>() {
            @Override
            public void onResponse(Call<HealthData[]> call, Response<HealthData[]> response) {
                Log.i("Retrofit", "Reponse: " + response);
                if (response.isSuccessful()) {
                    //Tager første data i array om healthData
                    HealthData healthData = response.body()[0];
                    Log.i("Retrofit", "SUCCESS!\nHealth Data: " + healthData);
                    randomHealthData.setValue(healthData);
                }
            }
            @Override
            public void onFailure(Call<HealthData[]> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (searchForHealthData)" + call
                        +"\nError Message: " + t.getMessage());
            }
        });
    }
}
