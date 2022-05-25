package com.example.sep4_android.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4_android.model.persistence.Database;
import com.example.sep4_android.model.persistence.DeviceRoomDAO;
import com.example.sep4_android.model.persistence.MeasurementDAO;
import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.DeviceDAO;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.webService.HealthAPI;
import com.example.sep4_android.webService.HealthServiceGenerator;
import com.example.sep4_android.webService.MeasurementsByRoomResponse;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthRepositoryWeb implements HealthRepository {
    private static HealthRepositoryWeb instance;
    private final MutableLiveData<List<Measurement>> randomHealthData;
    private HealthAPI healthAPI;

    //Skal disse være her? - DAO --> Data Persistance fra WEBAPI!
    private final MeasurementDAO measurementDAO;
    private final DeviceDAO deviceDAO;
    private final DeviceRoomDAO deviceRoomDAO;

    private final ExecutorService executorService;

    public HealthRepositoryWeb(Application application) {
        randomHealthData = new MutableLiveData<>();
        healthAPI = HealthServiceGenerator.getHealthAPI();

        Database database = Database.getInstance(application);
        measurementDAO = database.measurementDAO();
        deviceDAO = database.deviceDAO();
        deviceRoomDAO = database.deviceRoomDAO();

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
                Log.i("Retrofit", "FAILURE (getAllDevices)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
        //FIXME: lidt skørt, måske bare lave et seperat interface til RepositoryWeb i stedet? --> og kald denne findMeasurementsByDevice?
        return null;
    }

    @Override
    public LiveData<List<Measurement>> getMeasurementsBetweenTimestamps(Device device, long start, long end) {
        //TODO: http://sep4webapi-env.eba-2fmcgiei.eu-west-1.elasticbeanstalk.com/api/v1/Rooms/def/measurements?validFrom=0&validTo=1351351351
        return null;
    }

    @Override
    public void sendMaxMeasurementValues(Device device, int desiredTemp, int desiredCO2, int desiredHumidity) {
        //TODO: Mangler endpoint fra DAI
    }

    @Override
    public void updateClassroom(Device device) {
        Call<ResponseBody> call = healthAPI.putClassroomName(device.getRoomName(), device.getClimateDeviceId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("Retrofit", "Response: "+response);
                if(response.isSuccessful()){
                    Log.i("Retrofit", "Success response: "+response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Retrofit", "FAILURE (updateClassroom)" + call
                        + "\nError Message: " + t.getMessage());
            }
        });
        //Noget i stil af dette kald
        //healthAPI.setDeviceRoom(device.getRoomName(), device);
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice(Device device) {
        //Call<MeasurementsByRoomResponse[]> call = healthAPI.getAllMeasurementsByRoom("c02_02");
        Call<MeasurementsByRoomResponse[]> call = healthAPI.getAllMeasurementsByRoom(device.getRoomName());

        Log.i("Retrofit", "(getAllMeasurementsByDevice) - Call: " + call);
        call.enqueue(new Callback<MeasurementsByRoomResponse[]>() {
            @Override
            public void onResponse(Call<MeasurementsByRoomResponse[]> call, Response<MeasurementsByRoomResponse[]> response) {
                Log.i("Retrofit", "Reponse: " + response);
                if (response.isSuccessful()) {
                    //Tager første data i array om healthData
                    MeasurementsByRoomResponse[] measurementsByRoomResponseList = response.body();
                    Log.i("Retrofit", "SUCCESS!\nMeasurements: " + measurementsByRoomResponseList);

                    //randomHealthData.setValue(device);

                    executorService.execute(() -> {
                        for (MeasurementsByRoomResponse measurementByRoom: measurementsByRoomResponseList) {
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
        //FIXME: lidt skørt, måske bare lave et seperat interface til RepositoryWeb i stedet? --> og kald denne findMeasurementsByDevice?
        return null;
    }

    @Override
    public void addRoom(String roomName) {
        Call<ResponseBody> call = healthAPI.addRoom(new DeviceRoom(roomName));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("Retrofit", "Response (addRoom): "+response);
                if(response.isSuccessful()){
                    Log.i("Retrofit", "Success response (addRoom): "+response.body());
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
    public LiveData<List<DeviceRoom>> getAllRooms() {
        Call<DeviceRoom[]> call = healthAPI.getAllRooms();
        Log.i("Retrofit", "(getAllRooms) - Call: " + call);
        call.enqueue(new Callback<DeviceRoom[]>() {
            @Override
            public void onResponse(Call<DeviceRoom[]> call, Response<DeviceRoom[]> response) {
                Log.i("Retrofit", "(getAllRooms) Reponse: " + response);
                if (response.isSuccessful()) {
                    DeviceRoom[] deviceRooms = response.body();

                    Log.i("Retrofit", "SUCCESS!\nAmount of Rooms" + deviceRooms.length + "\ngetAllRooms: " + deviceRooms + "\nFirst Room: " + deviceRooms[0]);

                    //Gemmer data til room!
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
        //FIXME: fjern returtype?
        return null;
    }
}
