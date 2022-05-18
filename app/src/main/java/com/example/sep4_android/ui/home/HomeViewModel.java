package com.example.sep4_android.ui.home;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.Device;
import com.example.sep4_android.webService.HealthRepository;

import java.sql.Timestamp;

public interface HomeViewModel {
    LiveData<Device> getHealthDataBetweenTimeStamps(Timestamp timeStart, Timestamp timeEnd);
}
