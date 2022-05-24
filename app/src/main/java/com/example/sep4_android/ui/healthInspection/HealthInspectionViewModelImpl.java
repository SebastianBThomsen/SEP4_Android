package com.example.sep4_android.ui.healthInspection;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.repositories.Repository;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

import java.util.List;

public class HealthInspectionViewModelImpl extends AndroidViewModel implements HealthInspectionViewModel {
    RouteRepository repository;

    public HealthInspectionViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }


    @Override
    public LiveData<List<Measurement>> getAllHealthDataByDevice(String deviceId) {
        //TODO: optimering - bruger ikke deviceId
        return repository.getAllMeasurementsByDevice();
    }

    @Override
    public void findAllHealthDataByDevice(String deviceId) {
        repository.findAllMeasurementsByDevice(deviceId);
    }
}