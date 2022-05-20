package com.example.sep4_android.repositories;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

public interface Repository {
    LiveData<List<Measurement>> getAllMeasurements();
    LiveData<List<Measurement>> getHealthDataBetweenTimestamps(long start, long end);

    LiveData<Device> getAllHealthDataByDevice();
    void findAllHealthDataByDevice(String deviceId);

    void sendHealthSettings(int deviceId, int desiredTemp, int desiredCO2, int desiredHumidity);
    LiveData<Device> getHealthDataBetweenTimeStamps(long start, long end);
}
