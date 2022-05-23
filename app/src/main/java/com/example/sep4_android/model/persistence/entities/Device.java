package com.example.sep4_android.model.persistence.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NonNls;

import java.util.ArrayList;

@Entity(tableName = "device_table")
public class Device {
    @NonNull
    @PrimaryKey()
    private String deviceId;
    private String deviceRoom;

    public Device(String deviceId, String deviceRoom) {
        this.deviceRoom = deviceRoom;
        this.deviceId = deviceId;
    }

    @NonNull
    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceRoom() {
        return deviceRoom;
    }

    public void setDeviceRoom(String deviceRoom) {
        this.deviceRoom = deviceRoom;
    }
}
