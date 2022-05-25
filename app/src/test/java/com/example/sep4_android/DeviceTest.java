package com.example.sep4_android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.sep4_android.model.persistence.entities.Device;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DeviceTest {

    @Test
    public void deviceTest() {
        Device testDevice = new Device("Device101", "CO.2");

        assertEquals(true,testDevice.getDeviceId() == "Device101");
        assertEquals(true,testDevice.getDeviceRoom() == "CO.2");
        testDevice.setDeviceRoom("G2.9");
        assertEquals(true,testDevice.getDeviceRoom() == "G2.9");


        assertEquals(false,testDevice.getDeviceRoom() == "morten");
        assertEquals(false,testDevice.getDeviceId() == "peter");
    }
}
