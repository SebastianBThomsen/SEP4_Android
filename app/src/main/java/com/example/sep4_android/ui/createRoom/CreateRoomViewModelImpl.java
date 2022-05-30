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

    private final RouteRepository repository;

    public CreateRoomViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }

    @Override
    public String addRoom(String blockName, String floor, String roomNumber, String roomLetter) {
        String validate = validateRoomInput(blockName, floor, roomNumber, roomLetter);
        if (validate.equals("Valid")) {
            String roomName = blockName + floor + "_" + roomNumber + roomLetter;
            repository.addRoom(roomName);
        }
        return validate;
    }

    @Override
    public LiveData<List<DeviceRoom>> getAllRooms() {
        return repository.getAllRooms();
    }

    private String validateRoomInput(String blockName, String floor, String roomNumber, String roomLetter) {
        String message = "";
        if (blockName.length() != 1)
            message += "Block name invalid!\n";
        if (floor.length() != 2)
            message += "Floor invalid!\n";
        if (roomNumber.length() != 2)
            message += "Room number invalid!\n";
        if (roomLetter.length() != 0 && roomLetter.length() != 1)
            message += "room letter invalid!";
        if (message.equals(""))
            message = "Valid";
        //[A-Z][0-9][0-9]_[0-9][0-9][a-z]?$
        return message;
    }
}