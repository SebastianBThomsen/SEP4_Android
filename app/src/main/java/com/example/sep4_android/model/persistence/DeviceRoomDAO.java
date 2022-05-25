package com.example.sep4_android.model.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.sep4_android.model.persistence.entities.DeviceRoom;

import java.util.List;

@Dao
public interface DeviceRoomDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DeviceRoom deviceRoom);

    @Query("Select * FROM deviceRoom_table ORDER BY roomName DESC")
    LiveData<List<DeviceRoom>> getAllRooms();
}
