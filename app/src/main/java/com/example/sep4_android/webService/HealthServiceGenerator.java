package com.example.sep4_android.webService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HealthServiceGenerator {
    private static HealthAPI healthAPI;

    public static HealthAPI getHealthAPI() {
        if (healthAPI == null) {
            healthAPI = new Retrofit.Builder()
                    .baseUrl("http://sep4webapi-env.eba-2fmcgiei.eu-west-1.elasticbeanstalk.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(HealthAPI.class);
        }
        return healthAPI;

    }
}
