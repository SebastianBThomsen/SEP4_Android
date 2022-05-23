package com.example.sep4_android;


import static org.junit.Assert.assertEquals;

import com.example.sep4_android.repositories.HealthRepositoryWeb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HealthRepoWeb {

    @Test
    public void getInstance() {
       assertEquals(true, HealthRepositoryWeb.getInstance() == HealthRepositoryWeb.getInstance());
    }

    @Test
    public void getMeasurementsBetweenTimestamps() {
        //TODO: Lav testen når klassen er done
        assertEquals(true, false);
    }

    @Test
    public void getAllMeasurementsByDevice() {
        //TODO: Lav testen når klassen er done
        assertEquals(true, false);
    }

    @Test
    public void findAllMeasurementsByDevice() {
        //TODO: Lav testen når klassen er done
        assertEquals(true, false);
    }

    @Test
    public void sendMaxHealthSettingsValues() {
        //TODO: Lav testen når klassen er done
        assertEquals(true, false);
    }

    @Test
    public void findAllHealthDataByDevice() {
        //TODO: Lav testen når klassen er done
        assertEquals(true, false);
    }

}
