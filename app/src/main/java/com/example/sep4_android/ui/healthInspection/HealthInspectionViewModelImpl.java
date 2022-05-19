package com.example.sep4_android.ui.healthInspection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.Device;
import com.example.sep4_android.webService.HealthRepositoryWebImpl;

public class HealthInspectionViewModelImpl extends ViewModel implements HealthInspectionViewModel {
    HealthRepositoryWebImpl healthRepositoryImpl;

    public HealthInspectionViewModelImpl()
    {
        healthRepositoryImpl = HealthRepositoryWebImpl.getInstance();
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