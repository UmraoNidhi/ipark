package com.iparksimple.app.Activity.ui.Airport;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AirportViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AirportViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}