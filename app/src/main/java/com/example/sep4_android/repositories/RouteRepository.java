package com.example.sep4_android.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.model.persistence.entities.DeviceSettings;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

public interface RouteRepository {
    LiveData<List<Device>> getAllDevices();

    LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(long start, long end);
    LiveData<List<Measurement>> getAllMeasurementsByDevice();

    //Device Settings (max temp, co2, humidity, tempMargin)
    void sendDeviceSettings(int desiredCO2, int desiredHumidity, int desiredTemp, int desiredTempMargin);
    LiveData<DeviceSettings> getDeviceSettings();

    //Sætter dem så vi kan fetche dem i andre views
    void setSelectedDevice(Device device);
    void setSelectedUnregisteredDevice(Device device);
    void updateClassroom(String classroom);

    //Add Room
    void addRoom(String roomName);
    LiveData<List<DeviceRoom>> getAllRooms();

    //Device
    MutableLiveData<Device> getSelectedDeviceLive();
    Device getSelectedUnregisteredDevice();
    Device getSelectedDevice();
}
