package com.example.sep4_android.webService;

import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.model.persistence.entities.Measurement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//Old Mockup
//https://run.mocky.io/v3/01cae4fa-6f20-4ae7-9800-12a47935ae25

//mockup
//https://run.mocky.io/v3/d9384c83-1dd6-40b2-9c83-fa4f7ba54b15

public interface HealthAPI {
    /*
    @GET("api/v1/rooms/{roomNo}/measurements")
    Call<HealthData[]> getRandomHealthData(@Path("roomNo") int roomNo);
     */


    /*
    @GET("v3/{roomNo}")
    Call<MeasurementsByRoomResponse[]> getAllMeasurementsByRoom(@Path("roomNo") String roomNo);
*/
    @GET("api/v1/rooms/{roomNo}/measurements")
    Call<MeasurementsByRoomResponse[]> getAllMeasurementsByRoom(@Path("roomNo") String roomNo);


    /*
    //https://run.mocky.io/v3/?search=6f64b188-00ea-44b3-abaa-387588645afa
    @GET("v3/")
    Call<HealthData> getRandomTemperature(@Query("search") String search);
     */
}
