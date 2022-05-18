package com.example.sep4_android.webService;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.Device;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthRepositoryImpl implements HealthRepository {
    private static HealthRepositoryImpl instance;
    private final MutableLiveData<Device> randomHealthData;
    private HealthAPI healthAPI;

    public HealthRepositoryImpl() {
        randomHealthData = new MutableLiveData<>();
        healthAPI = HealthServiceGenerator.getHealthAPI();
    }

    public static synchronized HealthRepositoryImpl getInstance() {
        if (instance == null)
            instance = new HealthRepositoryImpl();
        return instance;
    }

    public LiveData<Device> getAllHealthDataByDevice() {
        return randomHealthData;
    }

    public void findAllHealthDataByDevice() {
        Log.i("Retrofit", "Start (searchForHealthData) - url: ");
        Call<Device> call = healthAPI.getAllHealthDataByDevice("b4830343-c4fe-4107-bae6-d229ccf8190c");
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

    @Override
    public void sendHealthSettings() {
        //TODO: Vi mangler end point fra DAI + at tilføje DeviceID eller Location?
    }

    @Override
    public LiveData<Device> getHealthDataBetweenTimeStamps(Timestamp timeStart, Timestamp timeEnd) {
        //TODO: dette skal bruge RIGTIG data!
        return randomHealthData;
    }
}
