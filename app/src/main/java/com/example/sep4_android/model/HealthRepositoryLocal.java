package com.example.sep4_android.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.persistence.MeasurementDAO;
import com.example.sep4_android.model.persistence.Database;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HealthRepositoryLocal {
    private static HealthRepositoryLocal instance;
    private MeasurementDAO measurementDAO;
    private ExecutorService executor;

    private LiveData<List<Measurement>> allMeasurements;
    private LiveData<List<Measurement>> measurementsTimestamps;

    private MutableLiveData<Measurement> averageMeasurement;


    private HealthRepositoryLocal(Application application){
        Database database = Database.getInstance(application);
        executor = Executors.newFixedThreadPool(2);

        averageMeasurement = new MutableLiveData<>();

        measurementDAO = database.measurementDAO();
        allMeasurements = measurementDAO.getAllMeasurements();

        //Observe average temps
        setAverageMeasurement();
    }

    public static synchronized HealthRepositoryLocal getInstance(Application application) {
        if (instance == null)
            instance = new HealthRepositoryLocal(application);
        return instance;
    }


    public MutableLiveData<Measurement> getAverageMeasurement() {
        return averageMeasurement;
    }

    private void setAverageMeasurement() {
        allMeasurements.observeForever(measurements -> {
            double co2=0, temp=0, humidity=0;
            if(measurements.size()>0)
            {
                for (Measurement measurement: measurements) {
                    co2 += measurement.getCo2();
                    temp += measurement.getTemperature();
                    humidity += measurement.getHumidity();
                }
                co2 = co2/measurements.size();
                temp = temp/measurements.size();
                humidity = humidity/measurements.size();
            }
            averageMeasurement.setValue(new Measurement(temp, co2, humidity, 0));
        } );
    }

    public LiveData<List<Measurement>> getAllMeasurements(){
        return allMeasurements;
    }

    public LiveData<List<Measurement>> getHealthDataBetweenTimestamps(long start, long end){
        Log.i("HealthRepositoryLocal", "getHealthDataBetweenTimestamps" + measurementDAO.getHealthDataBetweenTimestamps(start, end));
        return measurementDAO.getHealthDataBetweenTimestamps(start, end);
    }

    public void insert(Measurement measu){
        executor.execute(() -> {
            //measurementDAO.deleteAllNotes();
            measurementDAO.insert(measu);
        });
    }


}
