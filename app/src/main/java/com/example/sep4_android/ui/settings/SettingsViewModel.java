package com.example.sep4_android.ui.settings;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.DeviceSettings;

public interface SettingsViewModel {
    void sendDeviceSettings(int desiredCO2, int desiredHumidity, int desiredTemp, int desiredTempMargin);
    LiveData<DeviceSettings> getDeviceSettings();
}
