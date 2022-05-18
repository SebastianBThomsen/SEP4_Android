package com.example.sep4_android.ui.home;

import androidx.lifecycle.LiveData;

import com.example.sep4_android.model.Measurement;

import java.util.List;

public interface HomeViewModel {
    public LiveData<List<Measurement>> getMeasures();
}
