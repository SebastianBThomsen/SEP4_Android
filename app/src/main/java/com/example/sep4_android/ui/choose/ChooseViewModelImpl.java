package com.example.sep4_android.ui.choose;

import android.app.Application;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;

public class ChooseViewModelImpl extends AndroidViewModel {

    RouteRepository repository;

    public ChooseViewModelImpl(@NonNull Application application) {
        super(application);
        repository = RouteRepositoryImpl.getInstance(application);
    }

    // TODO: Implement the ViewModel
    public void checkSelected(Button btn){
        if(repository.getSelectedDevice() == null){
            btn.setVisibility(View.INVISIBLE);
        }
    }
}