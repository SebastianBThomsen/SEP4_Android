package com.example.sep4_android.ui.lineChartForCo2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.Device;
import com.example.sep4_android.webService.HealthRepository;

public class Co2ViewModelImpl extends ViewModel {
    private HealthRepository healthRepository;

    public Co2ViewModelImpl() {
        this.healthRepository = HealthRepository.getInstance();
    }

    public LiveData<Device> getAllHealthDataByDevice() {
        return healthRepository.getAllHealthDataByDevice();
    }

    public void findAllHealthDataByDevice() {
        healthRepository.findAllHealthDataByDevice();
    }
}