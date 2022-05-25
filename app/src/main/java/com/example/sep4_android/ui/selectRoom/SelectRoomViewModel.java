package com.example.sep4_android.ui.selectRoom;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;

import java.util.List;

public interface SelectRoomViewModel {
    void setSelectedDevice(Device device);
    LiveData<List<DeviceRoom>> getAllRooms();
}
