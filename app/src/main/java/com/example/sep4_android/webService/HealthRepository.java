package com.example.sep4_android.webService;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.Device;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthRepository {
    private static HealthRepository instance;
    private final MutableLiveData<Device> randomHealthData;
    private HealthAPI healthAPI;

    private HealthRepository() {
        randomHealthData = new MutableLiveData<>();
        healthAPI = HealthServiceGenerator.getHealthAPI();
    }

    public static synchronized HealthRepository getInstance() {
        if (instance == null)
            instance = new HealthRepository();
        return instance;
    }

    public MutableLiveData<Device> getAllHealthDataByDevice() {
        return randomHealthData;
    }

    public void findAllHealthDataByDevice() {
        Log.i("Retrofit", "Start (searchForHealthData) - url: ");
        Call<Device> call = healthAPI.getAllHealthDataByDevice("9479d1c2-bdf0-4ca4-9ff5-5c9d080ab019");
        Log.i("Retrofit", "(searchForHealthData) - Call: " + call);
        call.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                Log.i("Retrofit", "Reponse: " + response);
                if (response.isSuccessful()) {
                    //Tager første data i array om healthData
                    Device device = response.body();
                    Log.i("Retrofit", "SUCCESS!\nHealth Data: " + device);
                    randomHealthData.setValue(device);
                }
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (searchForHealthData)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
    }
}
