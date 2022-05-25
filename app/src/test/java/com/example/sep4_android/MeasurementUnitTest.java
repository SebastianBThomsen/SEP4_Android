package com.example.sep4_android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.sep4_android.model.persistence.entities.Measurement;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MeasurementUnitTest {
    private Measurement measurement;

    @Before
    public void setUp(){
        measurement = new Measurement("UnitDevice",15,43.2,10,15,1653308222);
    }

    @Test
    public void gettersTest ()
    {
        //Her tester vi vores getters for at se om de virker
        assertEquals(10,measurement.getCo2(),0.0);
        assertEquals(43.2,measurement.getTemperature(),0.0);
        assertEquals(15,measurement.getHumidity(),0.0);
        assertEquals(1653308222,measurement.getTimestamp(),0.0);
        assertEquals(15,measurement.getMeasurementId(),0.0);
        assertEquals("UnitDevice",measurement.getDeviceId());
    }

    @Test
    public void settersTest ()
    {
        measurement.setMeasurementId(76);
        assertEquals(76,measurement.getMeasurementId(),0.0);
        measurement.setCo2(54);
        assertEquals(54,measurement.getCo2(),0.0);

    }



  


}
