package com.example.sep4_android.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.webService.HealthAPI;
import com.example.sep4_android.webService.HealthServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthRepositoryWeb implements Repository {
    private static HealthRepositoryWeb instance;
    private final MutableLiveData<List<Measurement>> randomHealthData;
    private HealthAPI healthAPI;

    public HealthRepositoryWeb() {
        randomHealthData = new MutableLiveData<>();
        healthAPI = HealthServiceGenerator.getHealthAPI();
    }

    public static synchronized HealthRepositoryWeb getInstance() {
        if (instance == null)
            instance = new HealthRepositoryWeb();
        return instance;
    }

    @Override
    public LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(long start, long end) {
        return null;
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice(String deviceId) {
        return null;
    }

    @Override
    public void findAllMeasurementsByDevice(String deviceId) {

    }

    @Override
    public void sendMaxHealthSettingsValues(String deviceId, int desiredTemp, int desiredCO2, int desiredHumidity) {

    }

    public void findAllHealthDataByDevice() {
        /*
        FIXME: Skal have fundet løsning med vores nye model klasser!
        - Evt. HealthDataReponses?

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

         */
    }
}
