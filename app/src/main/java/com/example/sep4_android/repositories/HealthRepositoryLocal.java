package com.example.sep4_android.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.persistence.Database;
import com.example.sep4_android.model.persistence.DeviceDAO;
import com.example.sep4_android.model.persistence.DeviceRoomDAO;
import com.example.sep4_android.model.persistence.MeasurementDAO;
import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HealthRepositoryLocal implements HealthRepository {
    private static HealthRepositoryLocal instance;

    //DAOS
    private MeasurementDAO measurementDAO;
    private DeviceDAO deviceDAO;
    private DeviceRoomDAO deviceRoomDAO;

    //Multithread
    private ExecutorService executorService;

    private HealthRepositoryLocal(Application application) {
        Database database = Database.getInstance(application);

        //Initialize DAOs
        measurementDAO = database.measurementDAO();
        deviceDAO = database.deviceDAO();
        deviceRoomDAO = database.deviceRoomDAO();

        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized HealthRepositoryLocal getInstance(Application application) {
        if (instance == null)
            instance = new HealthRepositoryLocal(application);
        return instance;
    }

    @Override
    public LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(Device device, long start, long end) {
        return measurementDAO.getHealthDataBetweenTimestamps(device.getClimateDeviceId(), start, end);
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice(Device device) {
        return measurementDAO.getAllMeasurementsByDevice(device.getClimateDeviceId());
    }

    public LiveData<List<Device>> getAllDevices() {
        return deviceDAO.getAllDevices();
    }


    @Override
    public void sendMaxMeasurementValues(Device device, int desiredTemp, int desiredCO2, int desiredHumidity, int desiredTempMargin) {
        //TODO: Skal der sendes maxhealthsettings Til Room?? --> Måske noget med hvis internet er gået, så sender den med det samme internet kommer tilbage?

    }

    @Override
    public void updateClassroom(Device device) {
        executorService.execute(() -> {
            deviceDAO.updateClassroom(device.getClimateDeviceId(), device.getRoomName());
        });
    }

    @Override
    public void addRoom(String roomName) {
        executorService.execute(() -> {
            deviceRoomDAO.insert(new DeviceRoom(roomName));
        });
    }

    @Override
    public LiveData<List<DeviceRoom>> getAllRooms() {
        return deviceRoomDAO.getAllRooms();
    }
}
