package com.example.sep4_android.repositories;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

public interface HealthRepository {

    LiveData<List<Device>> getAllDevices();

    LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(Device device, long start, long end);
    LiveData<List<Measurement>> getAllMeasurementsByDevice(Device device);
    void sendMaxHealthSettingsValues(Device device, int desiredTemp, int desiredCO2, int desiredHumidity);
}
