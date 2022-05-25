package com.example.sep4_android.ui.createRoom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

import java.util.List;

public class CreateRoomViewModelImpl extends AndroidViewModel implements CreateRoomViewModel {

    RouteRepository repository;

    public CreateRoomViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }

    @Override
    public String addRoom(String roomName) {
        String validate = validateRoomInput(roomName);
        if (validate.equals("Valid"))
            repository.addRoom(roomName);
        return validate;
    }

    @Override
    public LiveData<List<DeviceRoom>> getAllRooms() {
        return repository.getAllRooms();
    }

    private String validateRoomInput(String roomName) {
        if (roomName.length() == 6 || roomName.length() == 7) {
            //[A-Z][0-9][0-9]_[0-9][0-9][a-z]?$
            return "Valid";
        }
        return "Invalid Input: Use [A-Z][0-9][0-9]_[0-9][0-9][a-z]";
    }
}