package com.example.sep4_android.ui.registerDevice;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

import java.util.List;

public class RegisterDeviceViewModelImpl extends AndroidViewModel implements RegisterDeviceViewModel {
    private RouteRepository repository;

    public RegisterDeviceViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }

    @Override
    public void register(String className) {
        repository.updateClassroom(className);
    }

    @Override
    public LiveData<List<DeviceRoom>> getAllRooms() {
        return repository.getAllRooms();
    }

    @Override
    public Device getSelectedUnregisteredDevice() {
        return repository.getSelectedUnregisteredDevice();
    }
}