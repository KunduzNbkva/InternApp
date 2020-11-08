package com.example.internapp;

import android.app.Application;

import com.example.internapp.data.PostApiService;

public class App extends Application {
    public static PostApiService service;
    @Override
    public void onCreate() {
        super.onCreate();
        service=new PostApiService();
    }
}
