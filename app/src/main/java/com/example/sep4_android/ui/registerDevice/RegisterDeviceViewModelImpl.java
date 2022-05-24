package com.example.sep4_android.ui.registerDevice;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

public class RegisterDeviceViewModelImpl extends AndroidViewModel {
    private RouteRepository repository;

    public RegisterDeviceViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }

    public void Register(String className){
        repository.updateClassroom(className);
    }
}