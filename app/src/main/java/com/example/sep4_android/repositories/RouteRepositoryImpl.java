package com.example.sep4_android.repositories;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.model.persistence.entities.DeviceSettings;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RouteRepositoryImpl implements RouteRepository {

    private static RouteRepositoryImpl instance;

    private HealthRepositoryWebImpl repositoryWeb;
    private HealthRepositoryLocalImpl repositoryLocal;
    private Application application;

    private ExecutorService executorService;

    private MutableLiveData<Device> selectedDeviceLive;
    private Device selectedDevice;
    private Device selectedUnregisteredDevice;

    public RouteRepositoryImpl(Application application) {
        this.application = application;
        repositoryWeb = HealthRepositoryWebImpl.getInstance(application);
        repositoryLocal = HealthRepositoryLocalImpl.getInstance(application);

        selectedDeviceLive = new MutableLiveData<>();

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

        selectedDeviceLive.postValue(selectedDevice);
    }

    public MutableLiveData<Device> getSelectedDeviceLive() {
        return selectedDeviceLive;
    }

    @Override
    public Device getSelectedUnregisteredDevice() {
        return selectedUnregisteredDevice;
    }

    public void setSelectedUnregisteredDevice(Device selectedUnregistedDevice) {
        this.selectedUnregisteredDevice = selectedUnregistedDevice;
    }

    @Override
    public LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(long start, long end) {
        if (isOnline()) {
            executorService.execute(() -> {
                repositoryWeb.findMeasurementsBetweenTimestamps(selectedDevice, start, end);
                //Stores data in Room
            });
        }
        //Gets data from Room
        return repositoryLocal.getMeasurementsBetweenTimestamps(selectedDevice, start, end);
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice() {
        if (isOnline()) {
            executorService.execute(() -> {
                //stores data in room from WebAPI
                repositoryWeb.findAllMeasurementsByDevice(selectedDevice);
            });
        }
        //returns Room Data
        return repositoryLocal.getAllMeasurementsByDevice(selectedDevice);
    }

    public LiveData<List<Device>> getAllDevices() {
        if (isOnline()) {
            executorService.execute(() -> {
                repositoryWeb.findAllDevices();
            });
        }
        return repositoryLocal.getAllDevices();
    }


    @Override
    public void sendDeviceSettings(int desiredTemp, int desiredCO2, int desiredHumidity,
                                   int desiredTempMargin) {
        if (isOnline()) {
            executorService.execute(() -> {
                repositoryWeb.sendDeviceSettings(selectedDevice, desiredTemp, desiredCO2, desiredHumidity, desiredTempMargin);
            });
        }
        repositoryLocal.sendDeviceSettings(selectedDevice, desiredTemp, desiredCO2, desiredHumidity, desiredTempMargin);
    }

    @Override
    public LiveData<DeviceSettings> getDeviceSettings() {
        if(isOnline())
            executorService.execute(()->{
                repositoryWeb.findDeviceSettings(selectedDevice.getClimateDeviceId());
            });
        return repositoryLocal.getDeviceSettings(selectedDevice.getClimateDeviceId());
    }

    public void updateClassroom(String classroom) {
        selectedUnregisteredDevice.setRoomName(classroom);
        if (isOnline())
            repositoryWeb.updateClassroom(selectedUnregisteredDevice);
        repositoryLocal.updateClassroom(selectedUnregisteredDevice);

        selectedUnregisteredDevice = null;
    }

    @Override
    public void addRoom(String roomName) {
        if (isOnline())
            repositoryWeb.addRoom(roomName);
        repositoryLocal.addRoom(roomName);
    }

    @Override
    public LiveData<List<DeviceRoom>> getAllRooms() {
        if (isOnline()) {
            executorService.execute(() -> {
                repositoryWeb.findAllRooms();
            });
        }
        return repositoryLocal.getAllRooms();
    }


    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
