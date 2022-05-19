package com.example.sep4_android.ui.healthInspection;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;

public interface HealthInspectionViewModel {
    LiveData<Device> getAllHealthDataByDevice();
    void findAllHealthDataByDevice();
}
