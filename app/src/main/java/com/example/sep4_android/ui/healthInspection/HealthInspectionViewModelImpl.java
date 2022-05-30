package com.example.sep4_android.ui.healthInspection;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class HealthInspectionViewModelImpl extends AndroidViewModel implements HealthInspectionViewModel {

    private RouteRepository repository;

    //Change livedata on new timeStamps selected --> Transformations.switchMap constructor
    private LiveData<List<Measurement>> measurementsByTimestamp;
    private MutableLiveData<List<Long>> filterTimestamp = new MutableLiveData<>();

    //Getting Avg, Min and Maxmimum measurements!
    private MutableLiveData<Measurement> minimumMeasurement;
    private MutableLiveData<Measurement> maximumMeasurement;
    private MutableLiveData<Measurement> averageMeasurement;
    private MutableLiveData<Measurement> latestMeasurement;


    public HealthInspectionViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);

        //For getting data in view
        minimumMeasurement = new MutableLiveData<>();
        maximumMeasurement = new MutableLiveData<>();
        averageMeasurement = new MutableLiveData<>();
        latestMeasurement = new MutableLiveData<>();

        //RXJava --> mere kompliceret
        measurementsByTimestamp = Transformations.switchMap(
                filterTimestamp,
                timestamp -> repository.getMeasurementsBetweenTimestamps(timestamp.get(0), timestamp.get(1))
        );
        observers();
    }

    private void observers() {
        measurementsByTimestamp.observeForever(measurements -> {
            if (measurements != null && measurements.size() > 0) {
                setMinMaxAvgLastMeasurement(measurements);
            } else {
                minimumMeasurement.postValue(new Measurement("MinimumMeasurementEmpty", 0, 0, 0, 0, 0));
                maximumMeasurement.postValue(new Measurement("MaximumMeasurementEmpty", 0, 0, 0, 0, 0));
                averageMeasurement.postValue(new Measurement("AverageMeasurementEmpty", 0, 0, 0, 0, 0));
                latestMeasurement.postValue(new Measurement("LastMeasurementEmpty", 0, 0, 0, 0, 0));
            }
        });
    }

    //Setting Mininimum, Maximum, Average and Latest measurement to MutableLiveData
    private void setMinMaxAvgLastMeasurement(List<Measurement> measurements) {
        double co2Avg = 0;
        double tempAvg = 0;
        double humidityAvg = 0;

        double co2Max = measurements.get(0).getCo2();
        double tempMax = measurements.get(0).getTemperature();
        double humidityMax = measurements.get(0).getHumidity();

        double co2Min = measurements.get(0).getCo2();
        double tempMin = measurements.get(0).getTemperature();
        double humidityMin = measurements.get(0).getHumidity();

        for (Measurement measurement : measurements) {
            //Find average
            co2Avg += measurement.getCo2();
            tempAvg += measurement.getTemperature();
            humidityAvg += measurement.getHumidity();

            //Find maximum
            if (measurement.getCo2() > co2Max)
                co2Max = measurement.getCo2();
            if (measurement.getTemperature() > tempMax)
                tempMax = measurement.getTemperature();
            if (measurement.getHumidity() > humidityMax)
                humidityMax = measurement.getHumidity();

            //Find minimum
            if (measurement.getCo2() < co2Min)
                co2Min = measurement.getCo2();
            if (measurement.getTemperature() < tempMin)
                tempMin = measurement.getTemperature();
            if (measurement.getHumidity() < humidityMin)
                humidityMin = measurement.getHumidity();
        }
        //Getting average!
        co2Avg /= measurements.size();
        tempAvg /= measurements.size();
        humidityAvg /= measurements.size();

        minimumMeasurement.postValue(new Measurement("MinimumMeasurement", 0, tempMin, co2Min, humidityMin, 0));
        maximumMeasurement.postValue(new Measurement("MaximumMeasurement", 0, tempMax, co2Max, humidityMax, 0));
        averageMeasurement.postValue(new Measurement("AverageMeasurement", 0, tempAvg, co2Avg, humidityAvg, 0));
        Measurement lastMeasurement = measurements.get(0);
        latestMeasurement.postValue(lastMeasurement);
    }

    public MutableLiveData<Measurement> getMinimumMeasurement() {
        return minimumMeasurement;
    }

    public MutableLiveData<Measurement> getMaximumMeasurement() {
        return maximumMeasurement;
    }

    public MutableLiveData<Measurement> getAverageMeasurement() {
        return averageMeasurement;
    }

    public MutableLiveData<Measurement> getLatestMeasurement() {
        return latestMeasurement;
    }

    public void setTimestamp(Long start, Long end) {
        ArrayList<Long> timestamps = new ArrayList<>();
        //Diving by 1000 to get in seconds instead of ms!

        //-3600*2=7200 --> Setting start of day!
        timestamps.add(start / 1000 - 3600 * 2);

        //+3600*22=79200 --> End of day!
        timestamps.add(end / 1000 + 3600 * 22);

        filterTimestamp.postValue(timestamps);
    }
}