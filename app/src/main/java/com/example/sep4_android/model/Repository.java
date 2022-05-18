package com.example.sep4_android.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.MeasurementDAO;
import com.example.sep4_android.model.persistence.Database;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private static Repository instance;
    private MeasurementDAO measurementDAO;
    private ExecutorService executor;

    private LiveData<List<Measurement>> allMeasurements;

    private Repository(Application application){
        Database database = Database.getInstance(application);
        executor = Executors.newFixedThreadPool(2);

        measurementDAO = database.measurementDAO();
        allMeasurements = measurementDAO.getAllMeasurements();
    }

    public static synchronized Repository getInstance(Application application) {
        if (instance == null)
            instance = new Repository(application);
        return instance;
    }

    public LiveData<List<Measurement>> getAllMeasurements(){
        return allMeasurements;
    }

    public void insert(Measurement measu){
        executor.execute(() -> measurementDAO.insert(measu));
    }


}
