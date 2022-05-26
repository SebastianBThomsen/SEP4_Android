package com.example.sep4_android.ui.graph;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

import java.util.List;

public class GraphViewModelImpl extends AndroidViewModel implements GraphViewModel {
    private RouteRepository repository;

    public GraphViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }

    @Override
    public LiveData<List<Measurement>> getAllMeasurementsByDevice() {
        return repository.getAllMeasurementsByDevice();
    }
}
