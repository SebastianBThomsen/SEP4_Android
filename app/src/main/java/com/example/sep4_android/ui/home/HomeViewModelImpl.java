package com.example.sep4_android.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.repositories.HealthRepositoryWeb;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModelImpl extends AndroidViewModel implements HomeViewModel {

    private RouteRepository repository;

    private LiveData<List<Measurement>> measurementsByTimestamp;
    private MutableLiveData<List<Long>> filterTimestamp = new MutableLiveData<>();


    public HomeViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);

        //RXJava --> mere kompliceret
        measurementsByTimestamp = Transformations.switchMap(
                filterTimestamp,
                timestamp -> repository.getMeasurementsBetweenTimestamps(timestamp.get(0), timestamp.get(1))
        );
    }

    @Override
    public LiveData<List<Measurement>> getHealthDataBetweenTimeStamps(long start, long end) {
        return repository.getMeasurementsBetweenTimestamps(start, end);
    }

    @Override
    public LiveData<List<Measurement>> getAllHealthDataByDevice() {
        return repository.getAllMeasurementsByDevice();
    }

    /*
    @Override
    public LiveData<Measurement> getAverageMeasurement(String deviceId){
        return repository.getAverageMeasurement(deviceId);
    }

     */

    @Override
    public LiveData<List<Measurement>> getTestMeasurements() {
        return measurementsByTimestamp;
    }

    public void setTimestamp(Long start, Long end) {
        ArrayList<Long> timestamps = new ArrayList<>();
        timestamps.add(start / 1000);
        timestamps.add(end / 1000);

        filterTimestamp.postValue(timestamps);
    }
}