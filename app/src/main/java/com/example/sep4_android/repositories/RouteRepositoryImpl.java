package com.example.sep4_android.repositories;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RouteRepositoryImpl implements RouteRepository {

    private static RouteRepositoryImpl instance;

    private HealthRepositoryWeb repositoryWeb;
    private HealthRepositoryLocal repositoryLocal;
    private Application application;

    private ExecutorService executorService;

    private Device selectedDevice;
    private Device selectedUnregistedDevice;

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
    public Device getSelectedUnregisteredDevice() {
        return selectedUnregistedDevice;
    }

    public void setSelectedUnregisteredDevice(Device selectedUnregistedDevice) {
        this.selectedUnregistedDevice = selectedUnregistedDevice;
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
    public void sendMaxMeasurementValues(int desiredTemp, int desiredCO2, int desiredHumidity, int desiredTempMargin) {
        executorService.execute(() -> {
            if (isOnline())
                repositoryWeb.sendMaxMeasurementValues(selectedDevice, desiredTemp, desiredCO2, desiredHumidity, desiredTempMargin);
        });
        //FIXME: Måske tilføj noget logik, der venter til device er online i en sekundær thread og så sender når dette er tilfældet?
        repositoryLocal.sendMaxMeasurementValues(selectedDevice, desiredTemp, desiredCO2, desiredHumidity, desiredTempMargin);
    }

    public void updateClassroom(String classroom) {
        selectedUnregistedDevice.setRoomName(classroom);

        if (isOnline()) {
            repositoryWeb.updateClassroom(selectedUnregistedDevice);
        }
        repositoryLocal.updateClassroom(selectedUnregistedDevice);

        selectedUnregistedDevice = null;
    }

    @Override
    public void addRoom(String roomName) {
        if (isOnline())
            repositoryWeb.addRoom(roomName);
        repositoryLocal.addRoom(roomName);
    }

    @Override
    public LiveData<List<DeviceRoom>> getAllRooms() {
        if (isOnline())
            repositoryWeb.getAllRooms();
        return repositoryLocal.getAllRooms();
    }



    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
