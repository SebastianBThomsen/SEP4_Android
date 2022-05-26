package com.example.sep4_android.model.persistence.entities;

public class DeviceSettings {
    private int co2Threshold, humidityThreshold, targetTemperature, temperatureMargin;

    public DeviceSettings(int co2Threshold, int humidityThreshold, int targetTemperature, int temperatureMargin) {
        this.co2Threshold = co2Threshold;
        this.humidityThreshold = humidityThreshold;
        this.targetTemperature = targetTemperature;
        this.temperatureMargin = temperatureMargin;
    }

    @Override
    public String toString() {
        return "DeviceSettings{" +
                "co2Threshold=" + co2Threshold +
                ", humidityThreshold=" + humidityThreshold +
                ", targetTemperature=" + targetTemperature +
                ", temperatureMargin=" + temperatureMargin +
                '}';
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
