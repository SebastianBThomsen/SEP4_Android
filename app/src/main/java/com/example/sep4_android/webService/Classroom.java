package com.example.sep4_android.webService;

public class Classroom {
    private String roomName;

    public Classroom(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "roomName='" + roomName + '\'' +
                '}';
    }
}
