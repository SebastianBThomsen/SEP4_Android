package com.example.sep4_android.ui.selectRoom;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.repositories.Repository;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

public class SelectRoomViewModelImpl extends AndroidViewModel implements SelectRoomViewModel {
    private RouteRepository repository;

    public SelectRoomViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }

    public void setSelectedDevice(Device device){
        repository.setSelectedDevice(device);
    }


}