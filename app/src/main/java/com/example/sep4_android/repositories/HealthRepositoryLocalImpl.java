package com.example.sep4_android.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.Database;
import com.example.sep4_android.model.persistence.DeviceDAO;
import com.example.sep4_android.model.persistence.DeviceRoomDAO;
import com.example.sep4_android.model.persistence.DeviceSettingsDAO;
import com.example.sep4_android.model.persistence.MeasurementDAO;
import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.model.persistence.entities.DeviceSettings;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HealthRepositoryLocalImpl implements HealthRepositoryLocal {
    private static HealthRepositoryLocalImpl instance;

    //DAOS
    private MeasurementDAO measurementDAO;
    private DeviceDAO deviceDAO;
    private DeviceRoomDAO deviceRoomDAO;
    private DeviceSettingsDAO deviceSettingsDAO;

    //Multithread
    private ExecutorService executorService;

    private HealthRepositoryLocalImpl(Application application) {
        Database database = Database.getInstance(application);

        //Initialize DAOs
        measurementDAO = database.measurementDAO();
        deviceDAO = database.deviceDAO();
        deviceRoomDAO = database.deviceRoomDAO();
        deviceSettingsDAO = database.deviceSettingsDAO();

        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized HealthRepositoryLocalImpl getInstance(Application application) {
        if (instance == null)
            instance = new HealthRepositoryLocalImpl(application);
        return instance;
    }

    @Override
    public LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(Device device, long start, long end) {
        return measurementDAO.getMeasurementsBetweenTimestamps(device.getClimateDeviceId(), start, end);
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice(Device device) {
        return measurementDAO.getAllMeasurementsByDevice(device.getClimateDeviceId());
    }

    public LiveData<List<Device>> getAllDevices() {
        return deviceDAO.getAllDevices();
    }


    @Override
    public void sendDeviceSettings(Device device, int desiredTemp, int desiredCO2, int desiredHumidity, int desiredTempMargin) {
        //TODO: Skal der sendes maxhealthsettings Til Room?? --> Måske noget med hvis internet er gået, så sender den med det samme internet kommer tilbage?

    }

    @Override
    public LiveData<DeviceSettings> getDeviceSettings(String deviceId) {
        return deviceSettingsDAO.getDeviceSettings(deviceId);
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
