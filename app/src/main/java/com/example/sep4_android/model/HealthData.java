package com.example.sep4_android.model;

public class HealthData {
    private double temperature, co2, humidity;
    //TODO: TIMESTAMP

    public HealthData(double temperature, double co2, double humidity) {
        this.temperature = temperature;
        this.co2 = co2;
        this.humidity = humidity;
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
}