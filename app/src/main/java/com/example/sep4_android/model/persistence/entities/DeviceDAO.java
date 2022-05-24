package com.example.sep4_android.model.persistence.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DeviceDAO {
    @Insert
    void insert(Device device);

    @Query("Select * FROM device_table ORDER BY deviceRoom DESC")
    LiveData<List<Device>> getAllDevices();

    @Query("Update device_table SET deviceRoom = :deviceRoom WHERE deviceId = :deviceId")
    void updateClassroom(String deviceId, String deviceRoom);

}
