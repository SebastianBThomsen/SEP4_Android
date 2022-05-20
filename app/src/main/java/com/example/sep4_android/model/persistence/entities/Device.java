package com.example.sep4_android.model.persistence.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NonNls;

import java.util.ArrayList;

@Entity(tableName = "device_table")
public class Device {
    //private String name, climate, gravity;
    //https://run.mocky.io/v3/139a8f39-d5dd-4826-a5a8-237ed5de88f6
    //GET Measurements endpoint: http://sep4webapi-env.eba-2fmcgiei.eu-west-1.elasticbeanstalk.com/api/v1/rooms/2/measurements

    @NonNull
    @PrimaryKey()
    private String deviceId;
    private String deviceRoom;


    public Device(String deviceId, String deviceRoom) {
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
