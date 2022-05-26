package com.example.sep4_android.ui.unregisteredDevices;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

import java.util.List;

public class UnregisteredDevicesViewModelImpl extends AndroidViewModel implements UnregisteredDevicesViewModel {
    private RouteRepository repository;

    public UnregisteredDevicesViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }


    @Override
    public void unregisteredDevice(Device device) {
        //TODO: Bruges denne?
    }

    @Override
    public LiveData<List<Device>> getAllDevices() {
        return repository.getAllDevices();
    }
}