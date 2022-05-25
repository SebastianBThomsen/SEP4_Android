package com.example.sep4_android.ui.createRoom;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.DeviceRoom;

import java.util.List;

public interface CreateRoomViewModel {
    String addRoom(String roomName);
    LiveData<List<DeviceRoom>> getAllRooms();
}
