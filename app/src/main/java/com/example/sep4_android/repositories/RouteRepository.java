package com.example.sep4_android.repositories;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

public class RouteRepository implements Repository {

    private static RouteRepository instance;

    private HealthRepositoryWeb repositoryWeb;
    private HealthRepositoryLocal repositoryLocal;
    private Application application;

    public RouteRepository(Application application) {
        this.application = application;
        repositoryWeb = HealthRepositoryWeb.getInstance();
        repositoryLocal = HealthRepositoryLocal.getInstance(application);
    }

    public static synchronized RouteRepository getInstance(Application application) {
        if (instance == null)
            instance = new RouteRepository(application);
        return instance;
    }

    @Override
    public LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(long start, long end) {
//        if (isOnline()) {
//            return repositoryWeb.getHealthDataBetweenTimestamps(start, end);
        //Store api call data
        //Check if id's exsist
//        }
        return repositoryLocal.getMeasurementsBetweenTimestamps(start, end);
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice(String deviceID) {
        if (isOnline()) {
            return repositoryWeb.getAllMeasurementsByDevice(deviceID);
        }
        return repositoryLocal.getAllMeasurementsByDevice(deviceID);
    }

    public LiveData<List<Device>> getAllDevices(){
//        if(isOnline()){
//
//        }

        return repositoryLocal.getAllDevices();
    }

    @Override
    public void findAllMeasurementsByDevice(String deviceId) {
        if (isOnline())
            repositoryWeb.findAllMeasurementsByDevice(deviceId);
        else
            repositoryLocal.findAllMeasurementsByDevice(deviceId);
    }

    @Override
    public void sendMaxHealthSettingsValues(String deviceId, int desiredTemp, int desiredCO2, int desiredHumidity) {
        if (isOnline())
            repositoryWeb.sendMaxHealthSettingsValues(deviceId, desiredTemp, desiredCO2, desiredHumidity);
        repositoryLocal.sendMaxHealthSettingsValues(deviceId, desiredTemp, desiredCO2, desiredHumidity);
    }


    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
