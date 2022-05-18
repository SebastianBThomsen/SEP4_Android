package com.example.sep4_android.ui.healthInspection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.Device;
import com.example.sep4_android.webService.HealthRepositoryImpl;

public class HealthInspectionViewModelImpl extends ViewModel implements HealthInspectionViewModel {
    HealthRepositoryImpl healthRepositoryImpl;

    public HealthInspectionViewModelImpl()
    {
        healthRepositoryImpl = HealthRepositoryImpl.getInstance();
    }


    @Override
    public LiveData<Device> getAllHealthDataByDevice() {
        return healthRepositoryImpl.getAllHealthDataByDevice();
    }

    @Override
    public void findAllHealthDataByDevice() {
        healthRepositoryImpl.findAllHealthDataByDevice();
    }
}