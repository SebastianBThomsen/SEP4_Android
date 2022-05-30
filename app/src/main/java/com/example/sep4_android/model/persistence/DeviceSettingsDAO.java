package com.example.sep4_android.model.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sep4_android.model.persistence.entities.DeviceSettings;

@Dao
public interface DeviceSettingsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DeviceSettings deviceSettings);

    @Query("Select * FROM deviceSettings_table WHERE deviceId = :deviceId")
    LiveData<DeviceSettings> getDeviceSettings(String deviceId);
}
