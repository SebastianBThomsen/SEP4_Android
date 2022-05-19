package com.example.sep4_android.model.persistence;

import android.icu.util.Measure;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sep4_android.model.Measurement;

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
}
