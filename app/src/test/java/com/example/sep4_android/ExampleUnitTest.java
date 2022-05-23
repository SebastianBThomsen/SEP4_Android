package com.example.sep4_android;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.sep4_android.model.persistence.entities.Device;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }







    //DEVICE Test
    public void deviceTest() {
        Device testDevice = new Device("Device101", "CO.2");
        assertTrue(testDevice.getDeviceId() == "Device101");


    }

}

