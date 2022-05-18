package com.example.sep4_android.ui.graph.lineChartForHumidity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.Device;
import com.example.sep4_android.webService.HealthRepository;

public class HumidityViewModelImpl extends ViewModel {
    private HealthRepository healthRepository;

    public HumidityViewModelImpl() {
        this.healthRepository = HealthRepository.getInstance();
    }

    public LiveData<Device> getAllHealthDataByDevice() {
        return healthRepository.getAllHealthDataByDevice();
    }

    public void findAllHealthDataByDevice() {
        healthRepository.findAllHealthDataByDevice();
    }
}