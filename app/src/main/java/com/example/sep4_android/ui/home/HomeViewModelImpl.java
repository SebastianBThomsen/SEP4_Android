package com.example.sep4_android.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModelImpl extends ViewModel implements HomeViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModelImpl() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}