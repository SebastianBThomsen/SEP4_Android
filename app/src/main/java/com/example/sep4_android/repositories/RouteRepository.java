package com.example.sep4_android.repositories;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

public interface RouteRepository {
    LiveData<List<Device>> getAllDevices();

    LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(long start, long end);
    LiveData<List<Measurement>> getAllMeasurementsByDevice();
    void sendMaxMeasurementValues(int desiredTemp, int desiredCO2, int desiredHumidity);

    //Sætter dem så vi kan fetche dem i andre views
    void setSelectedDevice(Device device);
    void setSelectedUnregistedDevice(Device device);
    void updateClassroom(String classroom);

    //Add Room
    void addRoom(String roomName);
    LiveData<List<DeviceRoom>> getAllRooms();

    //Device
    Device getSelectedUnregistedDevice();
}
