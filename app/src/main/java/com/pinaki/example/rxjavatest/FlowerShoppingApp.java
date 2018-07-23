package com.pinaki.example.rxjavatest;

import android.app.Application;

import com.pinaki.example.rxjavatest.di.ComponentFactory;

public class FlowerShoppingApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ComponentFactory.initialize(this);

    }
}
