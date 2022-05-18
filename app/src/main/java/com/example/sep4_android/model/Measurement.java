package com.example.sep4_android.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "measurements_table")
public class Measurement {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long date;

    private double temperature, co2, humidity;
    private String timestamp;
    //FIXME: TIMESTAMP --> Skal dette være et timestamp eller er String fint nok?

    public Measurement(double temperature, double co2, double humidity, String timestamp) {
        this.temperature = temperature;
        this.co2 = co2;
        this.humidity = humidity;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
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
