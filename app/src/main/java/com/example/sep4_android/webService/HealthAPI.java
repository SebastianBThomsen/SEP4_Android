package com.example.sep4_android.webService;

import com.example.sep4_android.model.HealthData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//https://run.mocky.io/                 v3/6f64b188-00ea-44b3-abaa-387588645afa

public interface HealthAPI {
    @GET("v3/{search}")
    Call<HealthData> getRandomTemperature(@Path("search") String search);
}
