package com.example.sep4_android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.sep4_android.model.persistence.entities.Device;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DeviceTest {


    @Test
    public void DeviceTest() {
        Device testDevice = new Device("Device101", "C0.2");  //TEST OBJECT
        assertEquals("Device{deviceId='Device101', deviceRoom='C0.2'}", testDevice.toString());
        assertEquals("Device101", testDevice.getClimateDeviceId());
        assertEquals("C0.2", testDevice.getRoomName());
        testDevice.setRoomName("Y9.9");
        assertEquals("Device{deviceId='Device101', deviceRoom='Y9.9'}", testDevice.toString());
        assertEquals("Y9.9", testDevice.getRoomName());
        assertNotEquals("C0.2", testDevice.getRoomName());
        assertNotEquals("Device{deviceId='Device101', deviceRoom='C0.2'}", testDevice.toString());

    }

    @Test
    public void toStringTest() {
        Device testDevice = new Device("Device101", "CO.2");  //TEST OBJECT
        assertEquals("Device{deviceId='Device101', deviceRoom='CO.2'}", testDevice.toString());
    }

    @Test
    public void getClimateDeviceIdTest() {
        Device testDevice = new Device("Device101", "CO.2");  //TEST OBJECT
        assertEquals("Device101", testDevice.getClimateDeviceId());
        Device testDevice2 = new Device(null, "CO.2");  //TEST OBJECT
        assertEquals(null, testDevice2.getClimateDeviceId());
    }

    @Test
    public void getRoomNameTest() {
        Device testDevice = new Device("Device101", "CO.2");  //TEST OBJECT
        assertEquals("CO.2", testDevice.getRoomName());
    }

    @Test
    public void setRoomNameTest() {
        Device testDevice = new Device("Device101", "CO.2");  //TEST OBJECT
        testDevice.setRoomName("P5.5");
        assertEquals("P5.5", testDevice.getRoomName());
        assertNotEquals("CO.2",testDevice.getRoomName());

        testDevice.setRoomName(null);
        assertEquals(null, testDevice.getRoomName());
    }
}
