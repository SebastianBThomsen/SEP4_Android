package com.example.sep4_android.model;

public class Measurement {
    private double temperature, co2, humidity;
    private String timestamp;
    //FIXME: TIMESTAMP --> Skal dette være et timestamp eller er String fint nok?

    public Measurement(double temperature, double co2, double humidity, String timestamp) {
        this.temperature = temperature;
        this.co2 = co2;
        this.humidity = humidity;
        this.timestamp = timestamp;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
