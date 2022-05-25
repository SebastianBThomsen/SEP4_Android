package com.example.sep4_android.model.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sep4_android.model.persistence.entities.Device;

import java.util.List;

@Dao
public interface DeviceDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Device device);

    @Query("Select * FROM device_table ORDER BY roomName DESC")
    LiveData<List<Device>> getAllDevices();

    @Query("Update device_table SET roomName = :deviceRoom WHERE climateDeviceId = :deviceId")
    void updateClassroom(String deviceId, String deviceRoom);

}
