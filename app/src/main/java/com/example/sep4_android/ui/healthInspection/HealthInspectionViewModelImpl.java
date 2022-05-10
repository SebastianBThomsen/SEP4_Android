package com.example.sep4_android.ui.healthInspection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.Device;
import com.example.sep4_android.webService.HealthRepository;

public class HealthInspectionViewModelImpl extends ViewModel implements HealthInspectionViewModel {
    HealthRepository healthRepository;

    public HealthInspectionViewModelImpl()
    {
        healthRepository = HealthRepository.getInstance();
    }


    @Override
    public LiveData<Device> getAllHealthDataByDevice() {
        return healthRepository.getAllHealthDataByDevice();
    }

    @Override
    public void findAllHealthDataByDevice() {
        healthRepository.findAllHealthDataByDevice();
    }
}