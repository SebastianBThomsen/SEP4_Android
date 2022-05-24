package com.example.sep4_android.model.persistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceDAO;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Measurement.class, Device.class}, version = 7)
public abstract class Database extends RoomDatabase {
    private static Database instance;
    public abstract MeasurementDAO measurementDAO();
    public abstract DeviceDAO deviceDAO();

    public static synchronized Database getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "measurement_database")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            //FIXME: Testing purposes, prepoulate data!
                            ExecutorService executorService = Executors.newFixedThreadPool(2);
                            Measurement measurement1 = new Measurement("bobTest",1,25, 1, 35, 1652948247);
                            Measurement measurement2 = new Measurement("bobTest",2,30, 2, 40, 1652937447);
                            Measurement measurement3 = new Measurement("bobTest",3,35, 3, 45, 1652872647);
                            Measurement measurement4 = new Measurement("bobTest",4,40, 4, 50, 1652786247);
                            Measurement measurement5 = new Measurement("bobTest",5,45, 5, 55, 1652699847);
                            Measurement measurement6 = new Measurement("bobTest2",6,45, 5, 55, 1652699847);
                            Measurement measurement7 = new Measurement("bobTest2",7,50, 10, 35, 1652699847);
                            Device device = new Device("bobTest", "C02.04");
                            Device device2 = new Device("bobTest2", "A03.12");
                            Device device3 = new Device("bobTest3", "B04.17");
                            Device device4 = new Device("bob1", "A01.01");
                            executorService.execute(() -> {
                                getInstance(context).deviceDAO().insert(device);
                                getInstance(context).deviceDAO().insert(device2);
                                getInstance(context).deviceDAO().insert(device3);
                                getInstance(context).deviceDAO().insert(device4);

                                getInstance(context).measurementDAO().insert(measurement1);
                                getInstance(context).measurementDAO().insert(measurement2);
                                getInstance(context).measurementDAO().insert(measurement3);
                                getInstance(context).measurementDAO().insert(measurement4);
                                getInstance(context).measurementDAO().insert(measurement5);
                                getInstance(context).measurementDAO().insert(measurement6);
                                getInstance(context).measurementDAO().insert(measurement7);
                            });
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
