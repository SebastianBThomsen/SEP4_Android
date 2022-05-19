package com.example.sep4_android.model.persistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.sep4_android.model.Measurement;

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
                            Measurement measurement1 = new Measurement(29, 0.1, 32, 1652876333);
                            Measurement measurement2 = new Measurement(27, 0.2, 30, 1652876666);
                            Measurement measurement3 = new Measurement(25, 0.3, 28, 1652876999);
                            executorService.execute(() -> {
                                getInstance(context).measurementDAO().insert(measurement1);
                                getInstance(context).measurementDAO().insert(measurement2);
                                getInstance(context).measurementDAO().insert(measurement3);
                            });
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
