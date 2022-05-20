package com.example.sep4_android.ui.healthInspection;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

public interface HealthInspectionViewModel {
    LiveData<List<Measurement>> getAllHealthDataByDevice(String deviceId);
    void findAllHealthDataByDevice(String deviceId);
}
