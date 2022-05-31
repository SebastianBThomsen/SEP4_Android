package com.example.sep4_android.ui.allDevices;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

import java.util.List;

public class AllDevicesViewModelImpl extends AndroidViewModel implements AllDevicesViewModel {

    private RouteRepository repository;

    public AllDevicesViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }

    @Override
    public LiveData<List<Device>> getAllDevices() {
        return repository.getAllDevices();
    }
}