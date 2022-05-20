package com.example.sep4_android.repositories;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.ArrayList;
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
    public LiveData<List<Measurement>> getHealthDataBetweenTimestamps(long start, long end) {
        //if (isOnline()) {
          //  return repositoryWeb.getHealthDataBetweenTimestamps(start, end);
        //}
        return repositoryLocal.getHealthDataBetweenTimestamps(start, end);
    }

    @Override
    public LiveData<List<Measurement>> getAllHealthDataByDevice(String deviceID) {
        if (isOnline()) {
            return repositoryWeb.getAllHealthDataByDevice(deviceID);
        }
        return repositoryLocal.getAllHealthDataByDevice(deviceID);
    }

    @Override
    public void findAllHealthDataByDevice(String deviceId) {
        if (isOnline())
            repositoryWeb.findAllHealthDataByDevice(deviceId);
        else
            repositoryLocal.findAllHealthDataByDevice(deviceId);
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
