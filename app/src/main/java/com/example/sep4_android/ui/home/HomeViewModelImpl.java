package com.example.sep4_android.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.Measurement;
import com.example.sep4_android.model.HealthRepositoryLocal;

import java.util.List;

import com.example.sep4_android.model.Device;
import com.example.sep4_android.webService.HealthRepositoryWeb;
import com.example.sep4_android.webService.HealthRepositoryWebImpl;

import java.sql.Timestamp;

public class HomeViewModelImpl extends AndroidViewModel implements HomeViewModel {

    private HealthRepositoryWeb healthRepositoryWeb;
    private HealthRepositoryLocal healthRepositoryLocal;

    public HomeViewModelImpl(@NonNull Application application) {
        super(application);
        healthRepositoryLocal = HealthRepositoryLocal.getInstance(application);
        healthRepositoryWeb = HealthRepositoryWebImpl.getInstance();
    }

    public LiveData<List<Measurement>> getMeasurements(){
        return healthRepositoryLocal.getAllMeasurements();
    }

    @Override
    public LiveData<Device> getHealthDataBetweenTimeStamps(Timestamp timeStart, Timestamp timeEnd) {
        return healthRepositoryWeb.getHealthDataBetweenTimeStamps(timeStart, timeEnd);
    }

    @Override
    public LiveData<List<Measurement>> getHealthDataBetweenTimestampsLocal(String start, String end) {
        long startTime = 0;
        long endTime = 0;
        try{
            startTime = Long.parseLong(start);
            endTime = Long.parseLong(end);
        }
        catch (Exception e){
            Log.i("HomeViewModelImpl", "Parsing startTime and endTime to long failed!");
        }

        return healthRepositoryLocal.getHealthDataBetweenTimestamps(startTime,endTime);
    }

    public LiveData<Measurement> getAverageMeasurement(){
        return healthRepositoryLocal.getAverageMeasurement();
    }

}