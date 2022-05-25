package com.example.sep4_android.model.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

@Dao
public interface MeasurementDAO {

    //Adding onClictIgnore --> So if data from API is already stored, IGNORE it!
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Measurement measurement);

    @Update
    void update(Measurement measurement);

    @Query("SELECT * FROM measurements_table WHERE deviceId = :deviceId ORDER BY timestamp DESC")
    LiveData<List<Measurement>> getAllMeasurementsByDevice(String deviceId);

    @Query("SELECT * FROM measurements_table WHERE deviceId = :deviceId AND timestamp BETWEEN :start AND :end ORDER BY timestamp DESC")
    LiveData<List<Measurement>> getHealthDataBetweenTimestamps(String deviceId, long start, long end);
}
