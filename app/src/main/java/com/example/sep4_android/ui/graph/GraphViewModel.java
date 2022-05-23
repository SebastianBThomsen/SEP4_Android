package com.example.sep4_android.ui.graph;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

public interface GraphViewModel {
    LiveData<List<Measurement>> getAllMeasurementsByDevice();
}
