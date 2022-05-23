package com.example.sep4_android.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.persistence.Database;
import com.example.sep4_android.model.persistence.MeasurementDAO;
import com.example.sep4_android.model.persistence.entities.Device;
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

public class HealthHealthRepositoryWeb implements HealthRepository {
    private static HealthHealthRepositoryWeb instance;
    private final MutableLiveData<List<Measurement>> randomHealthData;
    private HealthAPI healthAPI;

    //Denne skal nok ikke være her! - Data Persistance fra WEBAPI!
    private final MeasurementDAO measurementDAO;
    private final ExecutorService executorService;

    public HealthHealthRepositoryWeb(Application application) {
        randomHealthData = new MutableLiveData<>();
        healthAPI = HealthServiceGenerator.getHealthAPI();

        Database database = Database.getInstance(application);
        measurementDAO = database.measurementDAO();

        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized HealthHealthRepositoryWeb getInstance(Application application) {
        if (instance == null)
            instance = new HealthHealthRepositoryWeb(application);
        return instance;
    }


    @Override
    public LiveData<List<Device>> getAllDevices() {
        //TODO: Mangler endpoint fra DAI
        return null;
    }

    @Override
    public LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(Device device, long start, long end) {
        //TODO: Mangler endpoint fra DAI
        return null;
    }

    @Override
    public void sendMaxHealthSettingsValues(Device device, int desiredTemp, int desiredCO2, int desiredHumidity) {
        //TODO: Mangler endpoint fra DAI
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice(Device device) {
        //FIXME: Tjek MeasurementsByRoomResponse!
        //- Evt. HealthDataReponses?

        Log.i("Retrofit", "Start (searchForHealthData) - url: ");
        //localMockup
        //Call<MeasurementsByRoomResponse[]> call = healthAPI.getAllMeasurementsByRoom("d9384c83-1dd6-40b2-9c83-fa4f7ba54b15");
        Call<MeasurementsByRoomResponse[]> call = healthAPI.getAllMeasurementsByRoom("c02_02");
        Log.i("Retrofit", "(searchForHealthData) - Call: " + call);
        call.enqueue(new Callback<MeasurementsByRoomResponse[]>() {
            @Override
            public void onResponse(Call<MeasurementsByRoomResponse[]> call, Response<MeasurementsByRoomResponse[]> response) {
                Log.i("Retrofit", "Reponse: " + response);
                if (response.isSuccessful()) {
                    //Tager første data i array om healthData
                    MeasurementsByRoomResponse measurementsByRoomResponse = response.body()[0];
                    Log.i("Retrofit", "SUCCESS!\nHealth Data: " + measurementsByRoomResponse);
                    //randomHealthData.setValue(device);
                    //TODO: Kasper, er dette en fin måde at gemme data til cache på?
                    executorService.execute(() -> {
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
