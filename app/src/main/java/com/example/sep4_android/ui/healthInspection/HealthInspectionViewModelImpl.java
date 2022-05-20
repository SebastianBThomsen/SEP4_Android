package com.example.sep4_android.ui.healthInspection;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.repositories.HealthRepositoryWeb;
import com.example.sep4_android.repositories.Repository;
import com.example.sep4_android.repositories.RouteRepository;

import java.util.List;

public class HealthInspectionViewModelImpl extends AndroidViewModel implements HealthInspectionViewModel {
    Repository repository;

    public HealthInspectionViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepository.getInstance(application);
    }


    @Override
    public LiveData<List<Measurement>> getAllHealthDataByDevice(String deviceId) {
        return repository.getAllHealthDataByDevice(deviceId);
    }

    @Override
    public void findAllHealthDataByDevice(String deviceId) {
        repository.findAllHealthDataByDevice(deviceId);
    }
}