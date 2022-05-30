package com.example.sep4_android.repositories;

import android.app.Application;
import android.util.Log;

import com.example.sep4_android.model.persistence.Database;
import com.example.sep4_android.model.persistence.DeviceDAO;
import com.example.sep4_android.model.persistence.DeviceRoomDAO;
import com.example.sep4_android.model.persistence.DeviceSettingsDAO;
import com.example.sep4_android.model.persistence.MeasurementDAO;
import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.model.persistence.entities.DeviceSettings;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.webService.DeviceSettingsResponse;
import com.example.sep4_android.webService.HealthAPI;
import com.example.sep4_android.webService.HealthServiceGenerator;
import com.example.sep4_android.webService.MeasurementsByRoomResponse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthRepositoryWebImpl implements HealthRepositoryWeb {
    //Singleton
    private static HealthRepositoryWebImpl instance;
    //DAOs for saving data to Room!
    private final MeasurementDAO measurementDAO;
    private final DeviceDAO deviceDAO;
    private final DeviceRoomDAO deviceRoomDAO;
    private final DeviceSettingsDAO deviceSettingsDAO;
    private final ExecutorService executorService;
    //API
    private final HealthAPI healthAPI;

    public HealthRepositoryWebImpl(Application application) {
        healthAPI = HealthServiceGenerator.getHealthAPI();

        Database database = Database.getInstance(application);
        measurementDAO = database.measurementDAO();
        deviceDAO = database.deviceDAO();
        deviceRoomDAO = database.deviceRoomDAO();
        deviceSettingsDAO = database.deviceSettingsDAO();

        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized HealthRepositoryWebImpl getInstance(Application application) {
        if (instance == null)
            instance = new HealthRepositoryWebImpl(application);
        return instance;
    }


    @Override
    public void findAllDevices() {
        Call<Device[]> call = healthAPI.getAllDevices();
        call.enqueue(new Callback<Device[]>() {
            @Override
            public void onResponse(Call<Device[]> call, Response<Device[]> response) {
                if (response.isSuccessful()) {
                    Device[] devices = response.body();
                    //Saving data into Room on another thread!
                    executorService.execute(() -> {
                        for (Device device : devices) {
                            deviceDAO.insert(device);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Device[]> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (getAllDevices)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
    }

    @Override
    public void findMeasurementsBetweenTimestamps(Device device, long start, long end) {
        //API call
        Call<MeasurementsByRoomResponse[]> call = healthAPI.getMeasurementsBetweenTimestamps(device.getRoomName(), start, end);
        call.enqueue(new Callback<MeasurementsByRoomResponse[]>() {
            @Override
            public void onResponse(Call<MeasurementsByRoomResponse[]> call, Response<MeasurementsByRoomResponse[]> response) {
                if (response.isSuccessful()) {
                    //Saving response into measurementByRoomResponseList
                    MeasurementsByRoomResponse[] measurementsByRoomResponseList = response.body();
                    //Saving data into Room on another thread!
                    executorService.execute(() -> {
                        for (MeasurementsByRoomResponse measurementByRoom : measurementsByRoomResponseList) {
                            for (Measurement measurement : measurementByRoom.getMeasurements()) {
                                measurementDAO.insert(measurement);
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MeasurementsByRoomResponse[]> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (getAllMeasurementsByDevice)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
    }

    @Override
    public void sendDeviceSettings(DeviceSettings deviceSettings, String deviceRoom) {
        Call<ResponseBody> call = healthAPI.sendDeviceSettings(deviceSettings, deviceRoom);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("Retrofit", "Success response (addRoom): " + response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (addRoom)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
    }

    @Override
    public void findDeviceSettings(String deviceId) {
        Call<DeviceSettingsResponse> call = healthAPI.getDeviceSettings(deviceId);
        call.enqueue(new Callback<DeviceSettingsResponse>() {
            @Override
            public void onResponse(Call<DeviceSettingsResponse> call, Response<DeviceSettingsResponse> response) {
                if (response.isSuccessful()) {
                    //Saving response
                    DeviceSettingsResponse responseB = response.body();
                    //Converting to deviceSettings
                    DeviceSettings deviceSettings = new DeviceSettings(deviceId, responseB.getCo2Threshold(),
                            responseB.getHumidityThreshold(), responseB.getTargetTemperature(), responseB.getTemperatureMargin());
                    //Add data to Room
                    executorService.execute(() -> {
                        deviceSettingsDAO.insert(deviceSettings);

                    });
                }
            }

            @Override
            public void onFailure(Call<DeviceSettingsResponse> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (findMaxMeasurementValues)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
    }

    @Override
    public void updateClassroom(Device device) {
        Call<ResponseBody> call = healthAPI.putClassroomName(device.getRoomName(), device.getClimateDeviceId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("Retrofit", "Success response: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (updateClassroom)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
    }

    @Override
    public void findAllMeasurementsByDevice(Device device) {
        Call<MeasurementsByRoomResponse[]> call = healthAPI.getAllMeasurementsByRoom(device.getRoomName());

        call.enqueue(new Callback<MeasurementsByRoomResponse[]>() {
            @Override
            public void onResponse(Call<MeasurementsByRoomResponse[]> call, Response<MeasurementsByRoomResponse[]> response) {
                if (response.isSuccessful()) {
                    MeasurementsByRoomResponse[] measurementsByRoomResponseList = response.body();

                    //Saving data to room
                    executorService.execute(() -> {
                        for (MeasurementsByRoomResponse measurementByRoom : measurementsByRoomResponseList) {
                            for (Measurement measurement : measurementByRoom.getMeasurements()) {
                                measurementDAO.insert(measurement);
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MeasurementsByRoomResponse[]> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (getAllMeasurementsByDevice)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
    }

    @Override
    public void addRoom(String roomName) {
        Call<ResponseBody> call = healthAPI.addRoom(new DeviceRoom(roomName));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("Retrofit", "Success response (addRoom): " + response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (addRoom)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
    }

    @Override
    public void findAllRooms() {
        Call<DeviceRoom[]> call = healthAPI.getAllRooms();
        call.enqueue(new Callback<DeviceRoom[]>() {
            @Override
            public void onResponse(Call<DeviceRoom[]> call, Response<DeviceRoom[]> response) {
                if (response.isSuccessful()) {
                    DeviceRoom[] deviceRooms = response.body();
                    //Saving data to room
                    executorService.execute(() -> {
                        for (DeviceRoom deviceRoom : deviceRooms) {
                            deviceRoomDAO.insert(deviceRoom);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<DeviceRoom[]> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (getAllRooms)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
    }
}
