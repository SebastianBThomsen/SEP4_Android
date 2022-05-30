package com.example.sep4_android.model.persistence;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.model.persistence.entities.DeviceSettings;
import com.example.sep4_android.model.persistence.entities.Measurement;

@androidx.room.Database(entities = {Measurement.class, Device.class, DeviceRoom.class, DeviceSettings.class}, version = 12)
public abstract class Database extends RoomDatabase {
    private static Database instance;

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "measurement_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract MeasurementDAO measurementDAO();

    public abstract DeviceDAO deviceDAO();

    public abstract DeviceRoomDAO deviceRoomDAO();

    public abstract DeviceSettingsDAO deviceSettingsDAO();

}
