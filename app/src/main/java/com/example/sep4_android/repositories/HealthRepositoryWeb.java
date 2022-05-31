package com.example.sep4_android.repositories;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceSettings;

public interface HealthRepositoryWeb {
    //Get Devices
    void findAllDevices();

    //Get Measurements
    void findMeasurementsBetweenTimestamps(Device device, long start, long end);

    void findAllMeasurementsByDevice(Device device);

    //Setting
    void sendDeviceSettings(DeviceSettings deviceSettings, String deviceRoom);

    void findDeviceSettings(String deviceId);

    //Updating device room
    void updateClassroom(Device device);

    //add room
    void addRoom(String roomName);

    void findAllRooms();
}
