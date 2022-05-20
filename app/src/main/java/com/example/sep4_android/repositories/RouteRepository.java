package com.example.sep4_android.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

public class RouteRepository implements Repository {
    private HealthRepositoryWeb repositoryWeb;
    private HealthRepositoryLocal repositoryLocal;

    public RouteRepository(Application application) {
        repositoryWeb = HealthRepositoryWebImpl.getInstance();
        repositoryLocal = HealthRepositoryLocal.getInstance(application);
    }


    @Override
    public LiveData<List<Measurement>> getAllMeasurements() {
        return null;
    }

    @Override
    public LiveData<List<Measurement>> getHealthDataBetweenTimestamps(long start, long end) {
        return null;
    }

    @Override
    public LiveData<Device> getAllHealthDataByDevice() {
        return null;
    }

    @Override
    public void findAllHealthDataByDevice(String deviceId) {

    }

    @Override
    public void sendHealthSettings(int deviceId, int desiredTemp, int desiredCO2, int desiredHumidity) {

    }

    @Override
    public LiveData<Device> getHealthDataBetweenTimeStamps(long start, long end) {
        return null;
    }
}
