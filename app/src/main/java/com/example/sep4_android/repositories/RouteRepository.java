package com.example.sep4_android.repositories;

import com.example.sep4_android.model.persistence.entities.Device;

public interface RouteRepository extends Repository {
    void setSelectedDevice(Device device);
}
