package com.example.sep4_android.ui.healthInspection;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.HealthData;

public interface HealthInspectionViewModel {
    LiveData<HealthData> getRandomData();
    void findRandomHealthData();
}
