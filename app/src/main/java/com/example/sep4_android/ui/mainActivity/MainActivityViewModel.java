package com.example.sep4_android.ui.mainActivity;

import android.view.Menu;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;

public interface MainActivityViewModel {
    void DynamicNavigation(Menu navMenu);
    LiveData<Device> getSelectedDeviceLive();
    void updateGraphNav(Device device, Menu navMenu);
}
