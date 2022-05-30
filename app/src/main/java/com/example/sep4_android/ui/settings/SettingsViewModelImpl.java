package com.example.sep4_android.ui.settings;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.DeviceSettings;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

public class SettingsViewModelImpl extends AndroidViewModel implements SettingsViewModel {
    private RouteRepository repository;

    public SettingsViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }

    @Override
    public void sendSettings(int desiredCO2, int desiredHumidity, int desiredTemp, int desiredTempMargin) {
        repository.sendDeviceSettings(desiredCO2, desiredHumidity, desiredTemp, desiredTempMargin);
    }

    @Override
    public LiveData<DeviceSettings> getDeviceSettings() {
        return repository.getDeviceSettings();
    }
}