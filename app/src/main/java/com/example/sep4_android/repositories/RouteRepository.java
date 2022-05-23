package com.example.sep4_android.repositories;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

public interface RouteRepository {
    void setSelectedDevice(Device device);
    LiveData<List<Device>> getAllDevices();

    LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(long start, long end);
    LiveData<List<Measurement>> getAllMeasurementsByDevice();
    void sendMaxHealthSettingsValues(int desiredTemp, int desiredCO2, int desiredHumidity);
}
