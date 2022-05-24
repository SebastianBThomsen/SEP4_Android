package com.example.sep4_android.repositories;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RouteRepositoryImpl implements RouteRepository {

    private static RouteRepositoryImpl instance;

    private HealthHealthRepositoryWeb repositoryWeb;
    private HealthHealthRepositoryLocal repositoryLocal;
    private Application application;

    private ExecutorService executorService;

    private Device selectedDevice;

    public RouteRepositoryImpl(Application application) {
        this.application = application;
        repositoryWeb = HealthHealthRepositoryWeb.getInstance(application);
        repositoryLocal = HealthHealthRepositoryLocal.getInstance(application);

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
        executorService.execute(() -> {
            if (isOnline())
                repositoryWeb.getMeasurementsBetweenTimestamps(selectedDevice, start, end);
            //Stores data in Room
        });

        //Gets data from Room
        return repositoryLocal.getMeasurementsBetweenTimestamps(selectedDevice, start, end);
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice() {
        executorService.execute(() -> {
            if (isOnline()) {
                //FIXME: Er dette en fin metode til at cache data? --> Gemmer alt data i Room! --> får derefter data via LocalRepo!
                //stores data in room from WebAPI
                repositoryWeb.getAllMeasurementsByDevice(selectedDevice);
            }
        });
        //returns Room Data
        return repositoryLocal.getAllMeasurementsByDevice(selectedDevice);
    }

    public LiveData<List<Device>> getAllDevices() {
        executorService.execute(() -> {
            if (isOnline()) {
                repositoryWeb.getAllDevices();
            }
        });

        return repositoryLocal.getAllDevices();
    }

    @Override
    public void sendMaxHealthSettingsValues(int desiredTemp, int desiredCO2, int desiredHumidity) {
        executorService.execute(() -> {
                    if (isOnline())
                        repositoryWeb.sendMaxHealthSettingsValues(selectedDevice, desiredTemp, desiredCO2, desiredHumidity);
                });
        //FIXME: Måske tilføj noget logik, der venter til device er online i en sekundær thread og så sender når dette er tilfældet?
        repositoryLocal.sendMaxHealthSettingsValues(selectedDevice, desiredTemp, desiredCO2, desiredHumidity);
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
