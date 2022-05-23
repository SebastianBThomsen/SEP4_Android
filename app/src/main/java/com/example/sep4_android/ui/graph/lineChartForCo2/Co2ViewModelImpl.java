package com.example.sep4_android.ui.graph.lineChartForCo2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.Measurement;


public class Co2ViewModelImpl extends ViewModel {
    private HealthRepository healthRepository;

    public Co2ViewModelImpl() {
        this.healthRepository = HealthRepository.getInstance();
    }

    public LiveData<Measurement> getAllHealthDataByDevice() {
        return healthRepository.getAllHealthDataByDevice();
    }

    public void findAllHealthDataByDevice() {
        healthRepository.findAllHealthDataByDevice();
    }
}