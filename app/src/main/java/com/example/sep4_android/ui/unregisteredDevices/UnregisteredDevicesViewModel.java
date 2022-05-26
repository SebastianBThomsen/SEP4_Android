package com.example.sep4_android.ui.unregisteredDevices;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;

import java.util.List;

public interface UnregisteredDevicesViewModel {
    void unregisteredDevice(Device device);
    LiveData<List<Device>> getAllDevices();
}
