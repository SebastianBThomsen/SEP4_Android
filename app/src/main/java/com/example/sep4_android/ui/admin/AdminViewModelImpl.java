package com.example.sep4_android.ui.admin;

import android.app.Application;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

public class AdminViewModelImpl extends AndroidViewModel implements AdminViewModel {

    RouteRepository repository;

    public AdminViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }

    @Override
    public void checkSelected(Button btn) {
        if (repository.getSelectedDevice() == null) {
            btn.setVisibility(View.INVISIBLE);
        }
    }
}