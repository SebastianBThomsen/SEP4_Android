package com.example.sep4_android.repositories;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

public interface RouteRepository {
    LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(long start, long end);
    LiveData<List<Measurement>> getAllMeasurementsByDevice();
    void findAllMeasurementsByDevice(String deviceId);
    void sendMaxHealthSettingsValues(String deviceId, int desiredTemp, int desiredCO2, int desiredHumidity);

    void setSelectedDevice(Device device);
    void setSelectedUnregistedDevice(Device device);

    void updateClassroom(String classroom);
}
