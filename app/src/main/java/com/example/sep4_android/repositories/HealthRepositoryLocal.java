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

public class HealthRepositoryLocal implements HealthRepository {
    private static HealthRepositoryLocal instance;
    private MeasurementDAO measurementDAO;
    private DeviceDAO deviceDAO;
    private ExecutorService executorService;

    //skal denne bruges?
    private MutableLiveData<List<Measurement>> allMeasurementsByDevice;
    private LiveData<List<Device>> allDevice;

    private MutableLiveData<Measurement> averageMeasurement;


    private HealthRepositoryLocal(Application application) {
        Database database = Database.getInstance(application);

        //Bruges denne overhovedet?
        executorService = Executors.newFixedThreadPool(2);

        averageMeasurement = new MutableLiveData<>();

        //Skal denne bruges?
        allMeasurementsByDevice = new MutableLiveData<>();

        measurementDAO = database.measurementDAO();
        deviceDAO = database.deviceDAO();
//        allMeasurements = measurementDAO.getAllMeasurements();
        allDevice = deviceDAO.getAllDevices();

        //Observe average temps //FIXME: skal dette gøres her?
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
        allMeasurementsByDevice.observeForever(measurements -> {
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
            averageMeasurement.setValue(new Measurement("averageReturn", 1, temp, co2, humidity, System.currentTimeMillis()));
        });
    }

    @Override
    public LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(Device device, long start, long end) {
        //Tilføj en getter til measurementList uden parametre! --> Så den kan observes i fragment!
        // --> HomeViewModel
        /*
         measurementsByTimestamp = Transformations.switchMap(
                filterTimestamp,
                timestamp -> repository.getMeasurementsBetweenTimestamps(timestamp.get(0), timestamp.get(1))
        );
         */
        Log.i("HealthRepositoryLocal", "getHealthDataBetweenTimestamps" + measurementDAO.getHealthDataBetweenTimestamps(device.getClimateDeviceId(), start, end));
        return measurementDAO.getHealthDataBetweenTimestamps(device.getClimateDeviceId(), start, end);
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice(Device device) {
        //Returner her en liste af alle Measurements, da den førhen bare returnet null
        return measurementDAO.getAllMeasurementsByDevice(device.getClimateDeviceId());
    }

    public LiveData<List<Device>> getAllDevices() {
        return allDevice;
    }



    @Override
    public void sendMaxMeasurementValues(Device device, int desiredTemp, int desiredCO2, int desiredHumidity) {
        //TODO: Skal der sendes maxhealthsettings Til Room?? --> Måske noget med hvis internet er gået, så sender den med det samme internet kommer tilbage?

    }

    @Override
    public void updateClassroom(Device device){
        executorService.execute(
                () -> {
                    deviceDAO.updateClassroom(device.getDeviceId(), device.getDeviceRoom());
                }
        );
    }
}
