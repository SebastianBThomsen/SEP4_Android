package com.example.sep4_android.ui.healthInspection;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

public interface HealthInspectionViewModel {

    //FIXME: Denne bruges ikke!
    //LiveData<Measurement> getAverageMeasurement();

    //TEST
    LiveData<List<Measurement>> getTestMeasurements();
    void setTimestamp(Long start, Long end);
}
