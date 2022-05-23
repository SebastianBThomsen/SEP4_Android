package com.example.sep4_android.webService;

import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.ArrayList;
import java.util.List;

public class MeasurementsByRoomResponse {
    private String deviceId;
    private ArrayList<Measurement> measurements;

    public MeasurementsByRoomResponse(String deviceId, ArrayList<Measurement> measurements) {
        this.deviceId = deviceId;
        this.measurements = measurements;
    }

    public ArrayList<Measurement> getMeasurements()
    {
        ArrayList<Measurement> measures = measurements;
        for (Measurement measurement: measures) {
            measurement.setDeviceId(deviceId);
        }
        return measures;
    }

    @Override
    public String toString() {
        return "MeasurementsByRoomResponse{" +
                "deviceId='" + deviceId + '\'' +
                ", measurements=" + measurements +
                '}';
    }
}
