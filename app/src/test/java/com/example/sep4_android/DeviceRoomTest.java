package com.example.sep4_android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.sep4_android.model.persistence.entities.DeviceRoom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DeviceRoomTest {

    @Test
    public void deviceRoomTest() {
        DeviceRoom dr = new DeviceRoom("C0.2"); //Test object
        assertEquals("C0.2",dr.getRoomName());
        assertEquals("Room{roomName='C0.2'}",dr.toString());
        dr.setRoomName("L4.4");
        assertEquals("L4.4",dr.getRoomName());
        assertNotEquals("C0.2",dr.getRoomName());
        assertEquals("Room{roomName='L4.4'}",dr.toString());
        assertNotEquals("Room{roomName='C0.2'}",dr.toString());
    }

    @Test
    public void getRoomNameTest() {
        DeviceRoom dr = new DeviceRoom("C0.2"); //Test object
        assertEquals("C0.2",dr.getRoomName());
    }

    @Test
    public void setRoomNameTest() {
        DeviceRoom dr = new DeviceRoom("C0.2"); //Test object
        dr.setRoomName("P5.5");
        assertEquals("P5.5",dr.getRoomName());
        assertNotEquals("C0.2", dr.getRoomName());
    }

    @Test
    public void toStringTest() {
        DeviceRoom dr = new DeviceRoom("C0.2"); //Test object
        assertEquals("Room{roomName='C0.2'}",dr.toString());
        dr.setRoomName("P9.9");
        assertEquals("Room{roomName='P9.9'}",dr.toString());
        assertNotEquals("Room{roomName='C0.2'}",dr.toString());
    }


}
