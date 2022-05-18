package com.example.sep4_android.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.model.Measurement;
import com.example.sep4_android.model.Repository;

import java.util.List;

public class HomeViewModelImpl extends AndroidViewModel implements HomeViewModel {

    private final MutableLiveData<String> mText;

    //Test lort pls slet
    Measurement m = new Measurement(29.2, 0.1, 99.99, "2022-05-02 15:22");
    Repository r;

    public HomeViewModelImpl(@NonNull Application application) {
        super(application);
        r = Repository.getInstance(application);

        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        r.insert(m);
    }

    public LiveData<List<Measurement>> getMeasures(){
        return r.getAllMeasurements();
    }

    public LiveData<String> getText() {
        return mText;
    }
}