package com.example.sep4_android.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.sep4_android.model.DateHandler;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.model.HealthRepositoryLocal;

import java.util.List;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.webService.HealthRepositoryWeb;
import com.example.sep4_android.webService.HealthRepositoryWebImpl;

import java.sql.Timestamp;

public class HomeViewModelImpl extends AndroidViewModel implements HomeViewModel {

    private HealthRepositoryWeb healthRepositoryWeb;
    private HealthRepositoryLocal healthRepositoryLocal;

    private LiveData<List<Measurement>> measurementsByTimestamp;
    private MutableLiveData<Long> filterTimestamp = new MutableLiveData<>();


    public HomeViewModelImpl(@NonNull Application application) {
        super(application);
        healthRepositoryLocal = HealthRepositoryLocal.getInstance(application);
        healthRepositoryWeb = HealthRepositoryWebImpl.getInstance();

        measurementsByTimestamp = Transformations.switchMap(
                filterTimestamp,
                timestamp -> healthRepositoryLocal.getHealthDataBetweenTimestamps(timestamp, timestamp)
        );
    }

    public LiveData<List<Measurement>> getMeasurements(){
        return healthRepositoryLocal.getAllMeasurements();
    }

    @Override
    public LiveData<Device> getHealthDataBetweenTimeStamps(Timestamp timeStart, Timestamp timeEnd) {
        return healthRepositoryWeb.getHealthDataBetweenTimeStamps(timeStart, timeEnd);
    }

    @Override
    public LiveData<List<Measurement>> getHealthDataBetweenTimestampsLocal(long start, long end) {
        /*
        long startTime = 0;
        long endTime = 0;
        try{
            startTime = Long.parseLong(start);
            endTime = Long.parseLong(end);
        }
        catch (Exception e){
            Log.i("HomeViewModelImpl", "Parsing startTime and endTime to long failed!");
        }

         */
        Log.i("homeViewModelImpl", "Start time: " + start + "\n" + DateHandler.fromLongToString(start));
        Log.i("homeViewModelImpl", "Start time: " + end + "\n" + DateHandler.fromLongToString(end));

        return healthRepositoryLocal.getHealthDataBetweenTimestamps(start,end);
    }

    public LiveData<Measurement> getAverageMeasurement(){
        return healthRepositoryLocal.getAverageMeasurement();
    }

    @Override
    public LiveData<List<Measurement>> getTestMeasurements() {
        return measurementsByTimestamp;
    }

    void setTimestamp(String timestamp) {
        //filterTimestamp.setValue(timestamp);
    }

}