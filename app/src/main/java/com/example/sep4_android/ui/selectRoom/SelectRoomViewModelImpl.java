package com.example.sep4_android.ui.selectRoom;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.sep4_android.repositories.Repository;
import com.example.sep4_android.repositories.RouteRepository;

public class SelectRoomViewModelImpl extends AndroidViewModel implements SelectRoomViewModel {
    private Repository repository;

    public SelectRoomViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepository.getInstance(application);
    }


}