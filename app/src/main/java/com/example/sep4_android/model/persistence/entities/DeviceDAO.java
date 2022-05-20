package com.example.sep4_android.model.persistence.entities;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface DeviceDAO {
    @Insert
    void insert(Device device);
}
