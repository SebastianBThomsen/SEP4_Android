package com.example.sep4_android.model.persistence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

@Dao
public interface MeasurementDAO {
    @Insert
    void insert(Measurement measurement);

    @Update
    void update(Measurement measurement);

    @Delete
    void Delete(Measurement measurement);

    @Query("DELETE FROM measurements_table")
    void deleteAllNotes();

    @Query("SELECT * FROM measurements_table ORDER BY timestamp DESC")
    LiveData<List<Measurement>> getAllMeasurements();



    @Query("SELECT * FROM measurements_table WHERE timestamp BETWEEN :start AND :end ORDER BY timestamp DESC")
    LiveData<List<Measurement>> getHealthDataBetweenTimestamps(long start, long end);
}
