package com.example.sep4_android.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.webService.HealthAPI;
import com.example.sep4_android.webService.HealthServiceGenerator;
import com.example.sep4_android.webService.MeasurementsByRoomResponse;

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
        //FIXME: Skal have fundet løsning med vores nye model klasser!
        //- Evt. HealthDataReponses?

        Log.i("Retrofit", "Start (searchForHealthData) - url: ");
        //localMockup
        //Call<MeasurementsByRoomResponse[]> call = healthAPI.getAllMeasurementsByRoom("d9384c83-1dd6-40b2-9c83-fa4f7ba54b15");
        Call<MeasurementsByRoomResponse[]> call = healthAPI.getAllMeasurementsByRoom("c02_02");
        Log.i("Retrofit", "(searchForHealthData) - Call: " + call);
        call.enqueue(new Callback<MeasurementsByRoomResponse[]>() {
            @Override
            public void onResponse(Call<MeasurementsByRoomResponse[]> call, Response<MeasurementsByRoomResponse[]> response) {
                Log.i("Retrofit", "Reponse: " + response);
                if (response.isSuccessful()) {
                    //Tager første data i array om healthData
                    MeasurementsByRoomResponse measurementsByRoomResponse = response.body()[0];
                    Log.i("Retrofit", "SUCCESS!\nHealth Data: " + measurementsByRoomResponse);
                    //randomHealthData.setValue(device);
                }
            }

            @Override
            public void onFailure(Call<MeasurementsByRoomResponse[]> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (searchForHealthData)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });

    }
}
