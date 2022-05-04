package com.example.sep4_android.ui.healthInspection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.HealthData;
import com.example.sep4_android.webService.HealthRepository;

public class HealthInspectionViewModelImpl extends ViewModel implements HealthInspectionViewModel {
    HealthRepository healthRepository;

    public HealthInspectionViewModelImpl()
    {
        healthRepository = HealthRepository.getInstance();
    }


    @Override
    public LiveData<HealthData> getRandomData() {
        return healthRepository.getRandomHealthData();
    }

    @Override
    public void findRandomHealthData() {
        healthRepository.searchForHealthData("a85b58c4-24d7-4379-9ed9-b78f5d346a29");
    }
}