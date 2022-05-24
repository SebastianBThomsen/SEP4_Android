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
import com.example.sep4_android.webService.HealthAPI;
import com.example.sep4_android.webService.HealthServiceGenerator;
import com.example.sep4_android.webService.MeasurementsByRoomResponse;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthRepositoryWeb implements HealthRepository {
    private static HealthRepositoryWeb instance;
    private final MutableLiveData<List<Measurement>> randomHealthData;
    private HealthAPI healthAPI;

    //Denne skal nok ikke være her! - Data Persistance fra WEBAPI!
    private final MeasurementDAO measurementDAO;
    private final DeviceDAO deviceDAO;
    private final ExecutorService executorService;

    public HealthRepositoryWeb(Application application) {
        randomHealthData = new MutableLiveData<>();
        healthAPI = HealthServiceGenerator.getHealthAPI();

        Database database = Database.getInstance(application);
        measurementDAO = database.measurementDAO();
        deviceDAO = database.deviceDAO();

        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized HealthRepositoryWeb getInstance(Application application) {
        if (instance == null)
            instance = new HealthRepositoryWeb(application);
        return instance;
    }


    @Override
    public LiveData<List<Device>> getAllDevices() {
        Call<Device[]> call = healthAPI.getAllDevices();
        Log.i("Retrofit", "(getAllDevices) - Call: " + call);
        call.enqueue(new Callback<Device[]>() {
            @Override
            public void onResponse(Call<Device[]> call, Response<Device[]> response) {
                Log.i("Retrofit", "(getAllDevices) Reponse: " + response);
                if (response.isSuccessful()) {
                    //Tager første data i array om healthData
                    Device[] devices = response.body();
                    Log.i("Retrofit", "SUCCESS!\nAmount of devices" + devices.length + "\ngetAllDevices: " + devices + "\nFirst Device: " + devices[0]);
                    //randomHealthData.setValue(device);
                    //TODO: Kasper, er dette en fin måde at gemme data til cache på?
                    executorService.execute(() -> {
                        //FIXME: Dette gemmer kun measurements fra første MeasurementByRoomResponse eller?? --> Første device!
                        for (Device device : devices) {
                            deviceDAO.insert(device);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Device[]> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (searchForHealthData)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
        //FIXME: lidt skørt, måske bare lave et seperat interface til RepositoryWeb i stedet? --> og kald denne findMeasurementsByDevice?
        return null;
    }

    @Override
    public LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(Device device, long start, long end) {
        //TODO: Mangler endpoint fra DAI
        return null;
    }

    @Override
    public void sendMaxMeasurementValues(Device device, int desiredTemp, int desiredCO2, int desiredHumidity) {
        //TODO: Mangler endpoint fra DAI
    }

    @Override
    public void updateClassroom(Device device) {
        //TODO: Mangler endpoint

        //Noget i stil af dette kald
        //healthAPI.setDeviceRoom(device.getRoomName(), device);
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice(Device device) {
        //FIXME: Tjek MeasurementsByRoomResponse!
        //- Evt. HealthDataReponses?

        Log.i("Retrofit", "Start (getAllMeasurementsByDevice) - url: ");
        //localMockup
        //Call<MeasurementsByRoomResponse[]> call = healthAPI.getAllMeasurementsByRoom("d9384c83-1dd6-40b2-9c83-fa4f7ba54b15");
        Call<MeasurementsByRoomResponse[]> call = healthAPI.getAllMeasurementsByRoom("c02_02");
        Log.i("Retrofit", "(getAllMeasurementsByDevice) - Call: " + call);
        call.enqueue(new Callback<MeasurementsByRoomResponse[]>() {
            @Override
            public void onResponse(Call<MeasurementsByRoomResponse[]> call, Response<MeasurementsByRoomResponse[]> response) {
                Log.i("Retrofit", "Reponse: " + response);
                if (response.isSuccessful()) {
                    //Tager første data i array om healthData
                    MeasurementsByRoomResponse measurementsByRoomResponse = response.body()[0];
                    Log.i("Retrofit", "SUCCESS!\nMeasurements: " + measurementsByRoomResponse);
                    //randomHealthData.setValue(device);
                    //TODO: Kasper, er dette en fin måde at gemme data til cache på?
                    executorService.execute(() -> {
                        //FIXME: Dette gemmer kun measurements fra første MeasurementByRoomResponse eller?? --> Første device!
                        for (Measurement measurement : measurementsByRoomResponse.getMeasurements()) {
                            measurementDAO.insert(measurement);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MeasurementsByRoomResponse[]> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (searchForHealthData)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
        //FIXME: lidt skørt, måske bare lave et seperat interface til RepositoryWeb i stedet? --> og kald denne findMeasurementsByDevice?
        return null;
    }
}
