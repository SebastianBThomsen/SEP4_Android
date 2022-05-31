package com.example.sep4_android.webService;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;
import com.example.sep4_android.model.persistence.entities.DeviceSettings;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HealthAPI {

    @GET("api/v1/Rooms/{roomNo}/Measurements")
    Call<MeasurementsByRoomResponse[]> getAllMeasurementsByRoom(@Path("roomNo") String roomNo);

    //?validFrom=xxx&validTo=yyy
    @GET("api/v1/Rooms/{roomNo}/Measurements")
    Call<MeasurementsByRoomResponse[]> getMeasurementsBetweenTimestamps(@Path("roomNo") String roomNo,
                                                                        @Query("validFrom") long validFromSeconds,
                                                                        @Query("validTo") long validToSeconds);

    @GET("api/v1/Devices")
    Call<Device[]> getAllDevices();

    @GET("api/v1/Rooms")
    Call<DeviceRoom[]> getAllRooms();

    @PUT("api/v1/Rooms/{roomName}/devices/{deviceId}")
    Call<ResponseBody> putClassroomName(@Path("roomName") String classroom, @Path("deviceId") String deviceId);

    @POST("api/v1/Rooms")
    Call<ResponseBody> addRoom(@Body DeviceRoom deviceRoom);

    @PUT("api/v1/Rooms/{roomName}/settings")
    Call<ResponseBody> sendDeviceSettings(@Body DeviceSettings deviceSettings, @Path("roomName") String roomName);

    @GET("api/v1/Devices/{deviceId}/settings")
    Call<DeviceSettingsResponse> getDeviceSettings(@Path("deviceId") String deviceId);
}
