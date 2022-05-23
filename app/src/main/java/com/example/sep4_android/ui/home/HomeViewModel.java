package com.example.sep4_android.ui.home;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

import com.example.sep4_android.model.persistence.entities.Device;

import java.sql.Timestamp;

public interface HomeViewModel {
    LiveData<List<Measurement>> getHealthDataBetweenTimeStamps(long start, long end);
    LiveData<List<Measurement>> getAllHealthDataByDevice(String deviceId);
    //LiveData<Measurement> getAverageMeasurement();

    //TEST
    LiveData<List<Measurement>> getTestMeasurements();
    void setTimestamp(Long start, Long end);


    //TestWebAPI
    void findAllHealthDataByDevice();
}
