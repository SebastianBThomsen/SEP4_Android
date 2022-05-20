package com.example.sep4_android.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.repositories.HealthRepositoryLocal;

import java.util.ArrayList;
import java.util.List;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.repositories.HealthRepositoryWeb;
import com.example.sep4_android.repositories.HealthRepositoryWebImpl;

import java.sql.Timestamp;

public class HomeViewModelImpl extends AndroidViewModel implements HomeViewModel {

    private HealthRepositoryWeb healthRepositoryWeb;
    private HealthRepositoryLocal healthRepositoryLocal;

    private LiveData<List<Measurement>> measurementsByTimestamp;
    private MutableLiveData<List<Long>> filterTimestamp = new MutableLiveData<>();


    public HomeViewModelImpl(@NonNull Application application) {
        super(application);
        healthRepositoryLocal = HealthRepositoryLocal.getInstance(application);
        healthRepositoryWeb = HealthRepositoryWebImpl.getInstance();

        measurementsByTimestamp = Transformations.switchMap(
                filterTimestamp,
                timestamp -> healthRepositoryLocal.getHealthDataBetweenTimestamps(timestamp.get(0), timestamp.get(1))
        );
    }

    public LiveData<List<Measurement>> getMeasurements(){
        return healthRepositoryLocal.getAllMeasurements();
    }

    @Override
    public LiveData<Device> getHealthDataBetweenTimeStamps(long start, long end) {
        return healthRepositoryWeb.getHealthDataBetweenTimeStamps(start, end);
    }

    public LiveData<Measurement> getAverageMeasurement(){
        return healthRepositoryLocal.getAverageMeasurement();
    }

    @Override
    public LiveData<List<Measurement>> getTestMeasurements() {
        return measurementsByTimestamp;
    }

    public void setTimestamp(Long start, Long end) {
        ArrayList<Long> timestamps = new ArrayList<>();
        timestamps.add(start/1000);
        timestamps.add(end/1000);
        Log.i("HomeViewModelImpl", "setTimestamp + Start: " + start + " - END: " + end);
        Boolean isStartBefore = start<1652948247;
        Boolean isEndAfter = end>1652948247;

        Log.i("HomeViewModelImpl", "setTimestamp is start before: " + isStartBefore);
        Log.i("HomeViewModelImpl", "setTimestamp is end after: " + isEndAfter);
        filterTimestamp.postValue(timestamps);
    }

}