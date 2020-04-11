package com.example.asap_delivery.extra;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

public class List_data {
    private String mName;
    private Task<Uri> mImgUrl;
    public List_data(){

    }
    public List_data(String name, Task<Uri> imgUrl){
        if (name.trim().equals("")){
            name="No Name";
        }
        mName=name;
        mImgUrl=imgUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName=name;
    }

    public Task<Uri> getImgUrl() {
        return mImgUrl;
    }

    public void setImgUrl(Task<Uri> imgUrl) {
        mImgUrl=imgUrl;
    }
}