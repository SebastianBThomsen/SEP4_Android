package com.example.sep4_android.webService;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.Device;

import java.sql.Timestamp;

public interface HealthRepository {
    LiveData<Device> getAllHealthDataByDevice();
    void findAllHealthDataByDevice();
    void sendHealthSettings();
    LiveData<Device> getHealthDataBetweenTimeStamps(Timestamp timeStart, Timestamp timeEnd);
}
