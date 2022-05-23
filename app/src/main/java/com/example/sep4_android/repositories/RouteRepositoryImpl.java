package com.example.sep4_android.repositories;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RouteRepositoryImpl implements RouteRepository {

    private static RouteRepositoryImpl instance;

    private HealthRepositoryWeb repositoryWeb;
    private HealthRepositoryLocal repositoryLocal;
    private Application application;

    ExecutorService executorService;

    private Device selectedDevice;

    public RouteRepositoryImpl(Application application) {
        this.application = application;
        repositoryWeb = HealthRepositoryWeb.getInstance(application);
        repositoryLocal = HealthRepositoryLocal.getInstance(application);

        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized RouteRepositoryImpl getInstance(Application application) {
        if (instance == null)
            instance = new RouteRepositoryImpl(application);
        return instance;
    }

    public Device getSelectedDevice() {
        return selectedDevice;
    }

    public void setSelectedDevice(Device selectedDevice) {
        this.selectedDevice = selectedDevice;
    }

    @Override
    public LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(long start, long end) {
//        if (isOnline()) {
//            return repositoryWeb.getHealthDataBetweenTimestamps(start, end);
        //Store api call data
        //Check if id's exsist
//        }

        return repositoryLocal.getMeasurementsBetweenTimestamps(selectedDevice.getDeviceId(), start, end);
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice(String deviceID) {
        executorService.execute(() -> {
            if (isOnline()) {
                repositoryWeb.getAllMeasurementsByDevice(selectedDevice.getDeviceId());
            }
        });
        return repositoryLocal.getAllMeasurementsByDevice(selectedDevice.getDeviceId());
    }

    public LiveData<List<Device>> getAllDevices() {
//        if(isOnline()){
//
//        }

        return repositoryLocal.getAllDevices();
    }

    @Override
    public void findAllMeasurementsByDevice(String deviceId) {
        if (isOnline())
            repositoryWeb.findAllMeasurementsByDevice(selectedDevice.getDeviceId());
        else
            repositoryLocal.findAllMeasurementsByDevice(selectedDevice.getDeviceId());
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
