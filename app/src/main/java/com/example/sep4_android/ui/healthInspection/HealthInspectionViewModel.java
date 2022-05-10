package com.example.sep4_android.ui.healthInspection;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.Device;

public interface HealthInspectionViewModel {
    LiveData<Device> getRandomData();
    void findRandomHealthData();
}
