package com.example.sep4_android.ui.settings;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.sep4_android.repositories.Repository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

public class SettingsViewModelImpl extends AndroidViewModel implements SettingsViewModel {
    private Repository repository;

    public SettingsViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }


    @Override
    public void sendSettings(int desiredTemp, int desiredCO2, int desiredHumidity) {
        repository.sendMaxHealthSettingsValues("bobTest", desiredTemp, desiredCO2, desiredHumidity);
    }
    // TODO: Implement the ViewModel
}