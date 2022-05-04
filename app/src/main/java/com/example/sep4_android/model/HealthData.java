package com.example.sep4_android.model;

public class HealthData {
    private String name, climate, gravity;
    //private double temperature, co2, humidity;
    //TODO: TIMESTAMP


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public HealthData(String name, String climate, String gravity) {
        this.name = name;
        this.climate = climate;
        this.gravity = gravity;
    }
}
