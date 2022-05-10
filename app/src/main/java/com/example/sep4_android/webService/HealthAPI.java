package com.example.sep4_android.webService;

import com.example.sep4_android.model.Device;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//https://run.mocky.io/                 v3/01cae4fa-6f20-4ae7-9800-12a47935ae25

public interface HealthAPI {
    /*
    @GET("api/v1/rooms/{roomNo}/measurements")
    Call<HealthData[]> getRandomHealthData(@Path("roomNo") int roomNo);
     */


    @GET("v3/{roomNo}")
    Call<Device> getRandomHealthData(@Path("roomNo") String roomNo);



    /*
    //https://run.mocky.io/v3/?search=6f64b188-00ea-44b3-abaa-387588645afa
    @GET("v3/")
    Call<HealthData> getRandomTemperature(@Query("search") String search);
     */
}
