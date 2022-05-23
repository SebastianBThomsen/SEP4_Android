package com.example.sep4_android.ui.graph.lineChartForCo2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.repositories.RouteRepository;

import java.util.List;


public class Co2ViewModelImpl extends AndroidViewModel {
    private RouteRepository repository;

    public Co2ViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepository.getInstance(application);
    }


    public LiveData<List<Measurement>> getAllHealthDataByDevice() {
        return repository.getAllMeasurementsByDevice("bobtest");
    }

    public void findAllHealthDataByDevice() {
        repository.findAllMeasurementsByDevice("bobtest");
    }
}