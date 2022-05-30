package com.example.sep4_android.ui.settings;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.DeviceSettings;

public interface SettingsViewModel {
    void sendSettings(int desiredTemp, int desiredCO2, int desiredHumidity, int tempMargin);
    LiveData<DeviceSettings> getDeviceSettings();
}
