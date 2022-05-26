package com.example.sep4_android.ui.registerDevice;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;

import java.util.List;

public interface RegisterDeviceViewModel {
    void register(String className);
    LiveData<List<DeviceRoom>> getAllRooms();
    Device getSelectedUnregisteredDevice();
}
