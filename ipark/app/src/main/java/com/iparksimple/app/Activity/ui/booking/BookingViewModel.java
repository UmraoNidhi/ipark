package com.iparksimple.app.Activity.ui.booking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BookingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}