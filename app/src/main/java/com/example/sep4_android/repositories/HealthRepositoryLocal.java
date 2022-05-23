package com.example.sep4_android.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.persistence.Database;
import com.example.sep4_android.model.persistence.MeasurementDAO;
import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceDAO;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;

public class HealthRepositoryLocal implements Repository {
    private static HealthRepositoryLocal instance;
    private MeasurementDAO measurementDAO;
    private DeviceDAO deviceDAO;
    private ExecutorService executor;

    private LiveData<List<Measurement>> allMeasurements;
    private LiveData<List<Device>> allDevice;

    private MutableLiveData<Measurement> averageMeasurement;


    private HealthRepositoryLocal(Application application) {
        Database database = Database.getInstance(application);
        executor = Executors.newFixedThreadPool(2);

        averageMeasurement = new MutableLiveData<>();

        measurementDAO = database.measurementDAO();
        deviceDAO = database.deviceDAO();
        allMeasurements = measurementDAO.getAllMeasurements();
        allDevice = deviceDAO.getAllDevices();

        //Observe average temps
        setAverageMeasurement();


    }

    public static synchronized HealthRepositoryLocal getInstance(Application application) {
        if (instance == null)
            instance = new HealthRepositoryLocal(application);
        return instance;
    }


    public MutableLiveData<Measurement> getAverageMeasurement() {
        return averageMeasurement;
    }

    private void setAverageMeasurement() {
        allMeasurements.observeForever(measurements -> {
            double co2 = 0, temp = 0, humidity = 0;
            if (measurements.size() > 0) {
                for (Measurement measurement : measurements) {
                    co2 += measurement.getCo2();
                    temp += measurement.getTemperature();
                    humidity += measurement.getHumidity();
                }
                co2 = co2 / measurements.size();
                temp = temp / measurements.size();
                humidity = humidity / measurements.size();
            }
            averageMeasurement.setValue(new Measurement("averageReturn",1, temp, co2, humidity, System.currentTimeMillis()));
        });
    }

    public LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(String deviceId, long start, long end) {
        //FIXME: Dette virker ikke :((
        //measurementList = measurementDAO.getHealthDataBetweenTimestamps(start, end)
        //measurementList.setvalue(measurementDAO.getHealthDataBetweenTimestamps(start, end))

        //Tilføj en getter til measurementList uden parametre! --> Så den kan observes i fragment!
        Log.i("HealthRepositoryLocal", "getHealthDataBetweenTimestamps" + measurementDAO.getHealthDataBetweenTimestamps(deviceId, start, end));
        return measurementDAO.getHealthDataBetweenTimestamps(deviceId, start, end);
    }

    @Override
    public LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(long start, long end) {
        //bruger den ikke ^^
        return null;
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice(String deviceId)
    {
//Returner her en liste af alle Measurements, da den førhen bare returnet null
return allMeasurements;

    }

    //Er det ikke en duplicate?
    @Override
    public void findAllMeasurementsByDevice(String deviceId)
    {

    }

    public LiveData<List<Device>> getAllDevices(){
        return allDevice;
    }



    @Override
    public void sendMaxHealthSettingsValues(String deviceId, int desiredTemp, int desiredCO2, int desiredHumidity) {

    }
}
