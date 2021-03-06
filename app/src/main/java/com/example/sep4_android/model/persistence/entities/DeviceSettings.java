package com.example.sep4_android.model.persistence.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "deviceSettings_table")
public class DeviceSettings {

    @NonNull
    @PrimaryKey
    private String deviceId;

    private int co2Threshold, humidityThreshold, targetTemperature, temperatureMargin;

    public DeviceSettings(@NonNull String deviceId, int co2Threshold, int humidityThreshold, int targetTemperature, int temperatureMargin) {
        this.deviceId = deviceId;
        this.co2Threshold = co2Threshold;
        this.humidityThreshold = humidityThreshold;
        this.targetTemperature = targetTemperature;
        this.temperatureMargin = temperatureMargin;
    }

    @Override
    public String toString() {
        return "DeviceSettings{" +
                "deviceId='" + deviceId + '\'' +
                ", co2Threshold=" + co2Threshold +
                ", humidityThreshold=" + humidityThreshold +
                ", targetTemperature=" + targetTemperature +
                ", temperatureMargin=" + temperatureMargin +
                '}';
    }

    @NonNull
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(@NonNull String deviceId) {
        this.deviceId = deviceId;
    }

    public int getCo2Threshold() {
        return co2Threshold;
    }

    public void setCo2Threshold(int co2Threshold) {
        this.co2Threshold = co2Threshold;
    }

    public int getHumidityThreshold() {
        return humidityThreshold;
    }

    public void setHumidityThreshold(int humidityThreshold) {
        this.humidityThreshold = humidityThreshold;
    }

    public int getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(int targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public int getTemperatureMargin() {
        return temperatureMargin;
    }

    public void setTemperatureMargin(int temperatureMargin) {
        this.temperatureMargin = temperatureMargin;
    }
}
