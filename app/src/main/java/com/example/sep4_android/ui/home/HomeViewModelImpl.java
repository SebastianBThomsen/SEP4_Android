package com.example.sep4_android.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.Device;
import com.example.sep4_android.webService.HealthRepository;
import com.example.sep4_android.webService.HealthRepositoryImpl;

import java.sql.Timestamp;

public class HomeViewModelImpl extends ViewModel implements HomeViewModel {

    private HealthRepository repository;
    private final MutableLiveData<String> mText;

    public HomeViewModelImpl() {
        mText = new MutableLiveData<>();
        repository = HealthRepositoryImpl.getInstance();

        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    @Override
    public LiveData<Device> getHealthDataBetweenTimeStamps(Timestamp timeStart, Timestamp timeEnd) {
        return repository.getHealthDataBetweenTimeStamps(timeStart, timeEnd);
    }
}