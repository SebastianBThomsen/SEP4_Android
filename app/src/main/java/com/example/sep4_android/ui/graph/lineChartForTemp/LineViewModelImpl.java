package com.example.sep4_android.ui.graph.lineChartForTemp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;


import java.util.List;

public class LineViewModelImpl extends AndroidViewModel {
    private RouteRepository repository;

    public LineViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }


    public LiveData<List<Measurement>> getAllHealthDataByDevice() {
        return repository.getAllMeasurementsByDevice("bobtest");
    }

    public void findAllHealthDataByDevice() {
        repository.findAllMeasurementsByDevice("bobtest");
    }

}