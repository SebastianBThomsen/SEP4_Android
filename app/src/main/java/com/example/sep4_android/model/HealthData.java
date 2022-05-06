package com.example.sep4_android.model;

public class HealthData {
    //private String name, climate, gravity;
    //https://run.mocky.io/v3/139a8f39-d5dd-4826-a5a8-237ed5de88f6
    //GET Measurements endpoint: http://sep4webapi-env.eba-2fmcgiei.eu-west-1.elasticbeanstalk.com/api/v1/rooms/2/measurements
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
