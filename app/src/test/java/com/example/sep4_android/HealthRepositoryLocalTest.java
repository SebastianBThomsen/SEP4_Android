package com.example.sep4_android;

import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.repositories.HealthRepositoryLocal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

@RunWith(JUnit4.class)
public class HealthRepositoryLocalTest {
    private HealthRepositoryLocal healthRepositoryLocal;
    private Measurement measurement;

    @Before
    public void setUp(){
        ArrayList<Measurement> measurementArrayList = new ArrayList<>();
        measurementArrayList.add(new Measurement("bobTest",10,45.2,11,16,1653308222));
        measurementArrayList.add(new Measurement("bobTest2",11,44.2,12,15,1653308223));
        measurementArrayList.add(new Measurement("bobTest",12,43.2,13,14,1653308224));
        measurementArrayList.add(new Measurement("bobTest2",13,42.2,14,13,1653308225));
        measurementArrayList.add(new Measurement("bobTest",14,40.2,15,12,1653308226));
        measurementArrayList.add(new Measurement("bobTest2",15,41.2,16,11,1653308227));
    }
@Test
public void MaxHealthSettingsValues(){
        setUp();
        healthRepositoryLocal.sendMaxHealthSettingsValues("bobTest",25,300,30);
      // todo
}


    @Test
    public void testBetweenTimestamps(){
        setUp();

// todo Test ikke lavet
    }

    @Test
    public void testAverageMeasurement()
    {
        healthRepositoryLocal.getAverageMeasurement();
       // assertEquals(true, healthRepositoryLocal.getAverageMeasurement()=1);
        //Todo Test ikke lavet
    }

    @Test
    public void testGetAllMeasurementsByDevice()
    {
        //Todo Test ikke lavet
    }










}

