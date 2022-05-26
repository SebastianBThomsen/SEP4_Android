package com.example.sep4_android;


import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.repositories.HealthRepositoryLocal;
import com.example.sep4_android.repositories.RouteRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.app.Application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

@RunWith(JUnit4.class)
public class HealthRepositoryLocalTest {
    //---------------------------------------------------------------------------------------------------------------------------//
    //           !!--Cant figure out how to test class, since i cant instantiate the HealthRepositoryLocal Class--!!             //
    //---------------------------------------------------------------------------------------------------------------------------//
    //FIXME: Make test (Problem: Figure out how to instantiate the class

    private HealthRepositoryLocal healthRepositoryLocal;
    private Measurement measurement;
    ArrayList<Measurement> measurementArrayList = new ArrayList<>();

    @Before
    public void setUp() {
        measurementArrayList.add(new Measurement("bobTest", 10, 44.2, 21, 31, 1653308221));
        measurementArrayList.add(new Measurement("bobTest2", 11, 45.2, 22, 32, 1653308222));
        measurementArrayList.add(new Measurement("bobTest3", 12, 46.2, 23, 33, 1653308223));
        measurementArrayList.add(new Measurement("bobTest4", 13, 47.2, 24, 34, 1653308224));
        measurementArrayList.add(new Measurement("bobTest5", 14, 48.2, 25, 35, 1653308225));
        measurementArrayList.add(new Measurement("bobTest6", 15, 49.2, 26, 36, 1653308226));
    }

    @Test
    public void getInstance() {
        //HealthRepositoryLocal needs an application in its parameters - Don't know have to get one since its in a test.
        //HealthRepositoryLocal h = HealthRepositoryLocal.getInstance();
        //assertNotEquals(null, h);
    }


    @Test
    public void getAverageMeasurementTest() {
        //Cant test, since i can't figure out how to get instance of object
    }

    @Test
    public void getMeasurementsBetweenTimestampsTest() {
        //Cant test, since i can't figure out how to get instance of object
    }

    @Test
    public void getAllMeasurementsByDevice() {
        //Cant test, since i can't figure out how to get instance of object
    }

    @Test
    public void getAllDevicesTest() {
        //Cant test, since i can't figure out how to get instance of object
    }

    @Test
    public void sendMaxMeasurementValuesTest() {
        //Cant test, since i can't figure out how to get instance of object
    }

    @Test
    public void updateClassroomTest() {
        //Cant test, since i can't figure out how to get instance of object
    }

    @Test
    public void addRoomTest() {
        //Cant test, since i can't figure out how to get instance of object
    }

    @Test
    public void getAllRoomsTest() {
        //Cant test, since i can't figure out how to get instance of object
    }
}

