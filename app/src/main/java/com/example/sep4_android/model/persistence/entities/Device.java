package com.example.sep4_android.model.persistence.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "device_table")
public class Device {
    @NonNull
    @PrimaryKey()
    private String climateDeviceId;
    private String roomName;

    public Device(String climateDeviceId, String roomName) {
        this.roomName = roomName;
        this.climateDeviceId = climateDeviceId;
    }

    @Ignore
    public Device(String climateDeviceId){
        this.climateDeviceId = climateDeviceId;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId='" + climateDeviceId + '\'' +
                ", deviceRoom='" + roomName + '\'' +
                '}';
    }

    @NonNull
    public String getClimateDeviceId() {
        return climateDeviceId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
