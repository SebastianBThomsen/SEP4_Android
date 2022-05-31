package com.example.sep4_android.ui.createRoom;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.DeviceRoom;

import java.util.List;

public interface CreateRoomViewModel {
    String addRoom(String blockName, String floor, String roomNumber, String roomLetter);

    LiveData<List<DeviceRoom>> getAllRooms();
}
