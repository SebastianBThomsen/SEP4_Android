package com.example.sep4_android.ui.allDevices;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;

import java.util.List;

public interface AllDevicesViewModel {
    LiveData<List<Device>> getAllDevices();

}
