package com.example.sep4_android.repositories;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;

import java.sql.Timestamp;

public interface HealthRepositoryWeb {
    LiveData<Device> getAllHealthDataByDevice();
    void findAllHealthDataByDevice();
    void sendHealthSettings(int desiredTemp, int desiredCO2, int desiredHumidity);
    LiveData<Device> getHealthDataBetweenTimeStamps(long start, long end);
}
