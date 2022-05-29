package com.example.sep4_android.ui.healthInspection;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.persistence.entities.Measurement;

public interface HealthInspectionViewModel {
    MutableLiveData<Measurement> getMinimumMeasurement();
    MutableLiveData<Measurement> getMaximumMeasurement();
    MutableLiveData<Measurement> getAverageMeasurement();
    MutableLiveData<Measurement> getLatestMeasurement();

    void setTimestamp(Long start, Long end);
}
