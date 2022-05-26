package com.example.sep4_android;



import static org.junit.Assert.assertEquals;

import com.example.sep4_android.model.persistence.entities.Measurement;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MeasurementUnitTest {


    @Test
    public void allGettersTest ()
    {
        Measurement m = new Measurement("UnitDevice",15,43.2,10,15,1653308222);
        assertEquals(10,m.getCo2());
        assertEquals(43.2,m.getTemperature());
        assertEquals(15.0,m.getHumidity());
        assertEquals(1653308222,m.getTimestamp());
        assertEquals(15,m.getMeasurementId());
        assertEquals("UnitDevice",m.getDeviceId());
    }

    @Test
    public void allSettersTest ()
    {
        Measurement m = new Measurement("UnitDevice",15,43.2,10,15,1653308222);
        m.setMeasurementId(99);
        assertEquals(99, m.getMeasurementId(),0);

        m.setTemperature(98);
        assertEquals(98, m.getTemperature(),0);

        m.setCo2(97);
        assertEquals(97, m.getCo2(),0);

        m.setHumidity(96);
        assertEquals(96, m.getHumidity(),0);

        m.setTimestamp(94);
        assertEquals(94, m.getTimestamp());

        assertEquals("01/01-1970 - 01:00:00",m.getTimestampString());
  }

}
