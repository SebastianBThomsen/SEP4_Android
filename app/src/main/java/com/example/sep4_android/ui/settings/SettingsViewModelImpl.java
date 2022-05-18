package com.example.sep4_android.ui.settings;


import androidx.lifecycle.ViewModel;

import com.example.sep4_android.webService.HealthRepository;
import com.example.sep4_android.webService.HealthRepositoryImpl;

public class SettingsViewModelImpl extends ViewModel implements SettingsViewModel {
    private HealthRepository repository;

    public SettingsViewModelImpl() {
        this.repository = HealthRepositoryImpl.getInstance();
    }

    @Override
    public void sendSettings(int desiredTemp, int desiredCO2, int desiredHumidity) {
        repository.sendHealthSettings();
    }
    // TODO: Implement the ViewModel
}