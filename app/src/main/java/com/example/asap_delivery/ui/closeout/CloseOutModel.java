package com.example.asap_delivery.ui.closeout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CloseOutModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CloseOutModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is close out fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}