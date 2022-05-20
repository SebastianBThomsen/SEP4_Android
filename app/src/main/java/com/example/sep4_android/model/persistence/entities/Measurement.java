package com.example.sep4_android.model.persistence.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.example.sep4_android.model.DateHandler;

@Entity(tableName = "measurements_table", primaryKeys = {"deviceId", "measurementId"})
public class Measurement {
    private int measurementId;

    @NonNull
    private String deviceId;
    private long timestamp;

    private double temperature, co2, humidity;

    public Measurement(String deviceId, int measurementId , double temperature, double co2, double humidity, long timestamp) {
        this.temperature = temperature;
        this.co2 = co2;
        this.humidity = humidity;
        this.timestamp = timestamp;
    }
    //Getting timestamp in string format
    public String getTimestampString(){
        return DateHandler.fromLongToString(timestamp);
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "measurementId=" + measurementId +
                ", deviceId='" + deviceId + '\'' +
                ", timestamp=" + timestamp +
                ", temperature=" + temperature +
                ", co2=" + co2 +
                ", humidity=" + humidity +
                '}';
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
