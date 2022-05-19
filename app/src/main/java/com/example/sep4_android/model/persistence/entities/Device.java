package com.example.sep4_android.model.persistence.entities;

import java.util.ArrayList;

public class Device {
    //private String name, climate, gravity;
    //https://run.mocky.io/v3/139a8f39-d5dd-4826-a5a8-237ed5de88f6
    //GET Measurements endpoint: http://sep4webapi-env.eba-2fmcgiei.eu-west-1.elasticbeanstalk.com/api/v1/rooms/2/measurements
    private String deviceId;
    private ArrayList<Measurement> measurements;


    public Device(String deviceId) {
        this.deviceId = deviceId;
        measurements = new ArrayList<>();
    }

    public ArrayList<Measurement> getMeasurements() {
        return measurements;
    }

    public void addMeasurements(Measurement measurement) {
        measurements.add(measurement);
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
