package com.example.sep4_android.repositories;

import com.example.sep4_android.model.persistence.entities.Device;

public interface HealthRepositoryWeb {
    //Get Devices
    void findAllDevices();

    //Get Measurements
    void findMeasurementsBetweenTimestamps(Device device, long start, long end);
    void findAllMeasurementsByDevice(Device device);

    //Setting
    void sendMaxMeasurementValues(Device device, int desiredTemp, int desiredCO2, int desiredHumidity, int desiredTempMargin);
    void updateClassroom(Device device);

    //add room
    void addRoom(String roomName);
    void findAllRooms();
}
