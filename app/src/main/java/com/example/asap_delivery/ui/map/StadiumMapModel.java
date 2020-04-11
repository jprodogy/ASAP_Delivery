package com.example.asap_delivery.ui.map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StadiumMapModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StadiumMapModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is stadium map fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}