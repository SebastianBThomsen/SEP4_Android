package com.example.sep4_android.ui.graph.lineChart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.Device;
import com.example.sep4_android.webService.HealthRepository;

public class LineViewModelImpl extends ViewModel {
    HealthRepository healthRepository;


    public LiveData<Device> getAllHealthDataByDevice() {
        return healthRepository.getAllHealthDataByDevice();
    }

    public void findAllHealthDataByDevice() {
        healthRepository.findAllHealthDataByDevice();
    }

}