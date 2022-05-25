package com.example.sep4_android.model.persistence.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "deviceRoom_table")
public class DeviceRoom {

    @NonNull
    @PrimaryKey
    private String roomName;

    public DeviceRoom(@NonNull String roomName) {
        this.roomName = roomName;
    }

    @NonNull
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(@NonNull String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomName='" + roomName + '\'' +
                '}';
    }
}
