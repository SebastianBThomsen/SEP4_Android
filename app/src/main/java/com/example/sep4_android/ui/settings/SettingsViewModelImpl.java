package com.example.sep4_android.ui.settings;


import androidx.lifecycle.ViewModel;

import com.example.sep4_android.repositories.HealthRepositoryWeb;
import com.example.sep4_android.repositories.HealthRepositoryWebImpl;

public class SettingsViewModelImpl extends ViewModel implements SettingsViewModel {
    private HealthRepositoryWeb repository;

    public SettingsViewModelImpl() {
        this.repository = HealthRepositoryWebImpl.getInstance();
    }

    @Override
    public void sendSettings(int desiredTemp, int desiredCO2, int desiredHumidity) {
        repository.sendHealthSettings(desiredTemp, desiredCO2, desiredHumidity);
    }
    // TODO: Implement the ViewModel
}