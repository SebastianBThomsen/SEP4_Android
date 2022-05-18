package com.example.sep4_android.model.persistence;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sep4_android.model.Measurement;

@androidx.room.Database(entities = {Measurement.class}, version =1)
public abstract class Database extends RoomDatabase {
    private static Database instance;
    public abstract MeasurementDAO measurementDAO();

    public static synchronized Database getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "measurement_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
