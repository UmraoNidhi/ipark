package com.iparksimple.app.Activity.ui.Vehicles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VehicleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VehicleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}