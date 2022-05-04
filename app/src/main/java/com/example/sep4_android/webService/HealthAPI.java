package com.example.sep4_android.webService;

import com.example.sep4_android.model.HealthData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HealthAPI {
    @GET("v3/{search}")
    Call<HealthData> getRandomTemperature(@Path("search") String search);
}
