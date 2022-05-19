package com.example.sep4_android.model.persistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Measurement.class}, version = 3)
public abstract class Database extends RoomDatabase {
    private static Database instance;
    public abstract MeasurementDAO measurementDAO();

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
                            Measurement measurement1 = new Measurement(25, 1, 35, 1652948247);
                            Measurement measurement2 = new Measurement(30, 2, 40, 1652937447);
                            Measurement measurement3 = new Measurement(35, 3, 45, 1652872647);
                            Measurement measurement4 = new Measurement(40, 4, 50, 1652786247);
                            Measurement measurement5 = new Measurement(45, 5, 55, 1652699847);
                            executorService.execute(() -> {
                                getInstance(context).measurementDAO().insert(measurement1);
                                getInstance(context).measurementDAO().insert(measurement2);
                                getInstance(context).measurementDAO().insert(measurement3);
                                getInstance(context).measurementDAO().insert(measurement4);
                                getInstance(context).measurementDAO().insert(measurement5);
                            });
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
