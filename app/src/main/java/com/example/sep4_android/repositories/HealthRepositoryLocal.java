package com.example.sep4_android.repositories;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.model.persistence.entities.DeviceSettings;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

public interface HealthRepositoryLocal {
    //Get Devices
    LiveData<List<Device>> getAllDevices();

    //Get Measurements
    LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(Device device, long start, long end);
    LiveData<List<Measurement>> getAllMeasurementsByDevice(Device device);

    //Setting
    void sendDeviceSettings(DeviceSettings deviceSettings);
    LiveData<DeviceSettings> getDeviceSettings(String deviceId);
    void updateClassroom(Device device);

    //add room
    void addRoom(String roomName);
    LiveData<List<DeviceRoom>> getAllRooms();

}
