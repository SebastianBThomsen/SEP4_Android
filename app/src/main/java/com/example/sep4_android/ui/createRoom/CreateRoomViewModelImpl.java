package com.example.sep4_android.ui.createRoom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

public class CreateRoomViewModelImpl extends AndroidViewModel implements CreateRoomViewModel {

    RouteRepository repository;

    public CreateRoomViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }

    @Override
    public void addRoom(String roomName) {

    }
    // TODO: Implement the ViewModel
}