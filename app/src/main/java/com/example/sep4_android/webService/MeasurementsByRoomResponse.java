package com.example.sep4_android.webService;

import com.example.sep4_android.model.DateHandler;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.ArrayList;

public class MeasurementsByRoomResponse {
    private String deviceId;
    private ArrayList<MeasurementResponse> measurements;

    public MeasurementsByRoomResponse(String deviceId, ArrayList<MeasurementResponse> measurements) {
        this.deviceId = deviceId;
        this.measurements = measurements;
    }

    public ArrayList<Measurement> getMeasurements()
    {
        ArrayList<Measurement> measures = new ArrayList<>();

        //Converting WebAPI measurements to right format
        for (MeasurementResponse measurement: measurements) {

            Measurement measurementReturn = new Measurement(deviceId,
                    measurement.getMeasurementId(),
                    measurement.getTemperature(),
                    measurement.getCo2(),
                    measurement.getHumidity(),
                    DateHandler.fromStringToLong(measurement.getTimestamp())
                    );

            measures.add(measurementReturn);
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

    private class MeasurementResponse {
        private final int measurementId;
        private final String timestamp;
        private final double temperature;
        private final double co2;
        private final double humidity;

        public MeasurementResponse(int measurementId , double temperature, double co2, double humidity, String timestamp) {
            this.measurementId = measurementId;
            this.temperature = temperature;
            this.co2 = co2;
            this.humidity = humidity;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "Measurement{" +
                    "measurementId=" + measurementId +
                    ", timestamp=" + timestamp +
                    ", temperature=" + temperature +
                    ", co2=" + co2 +
                    ", humidity=" + humidity +
                    '}';
        }

        public int getMeasurementId() {
            return measurementId;
        }

        public double getTemperature() {
            return temperature;
        }

        public double getCo2() {
            return co2;
        }

        public double getHumidity() {
            return humidity;
        }

        public String getTimestamp() {
            return timestamp;
        }
    }
}
