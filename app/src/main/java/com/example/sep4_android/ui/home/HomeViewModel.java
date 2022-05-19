package com.example.sep4_android.ui.home;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.Measurement;

import java.util.List;

import com.example.sep4_android.model.Device;

import java.sql.Timestamp;

public interface HomeViewModel {
    LiveData<Device> getHealthDataBetweenTimeStamps(Timestamp timeStart, Timestamp timeEnd);
    LiveData<List<Measurement>> getHealthDataBetweenTimestampsLocal(String start, String end);
    LiveData<List<Measurement>> getMeasurements();
    LiveData<Measurement> getAverageMeasurement();
}
