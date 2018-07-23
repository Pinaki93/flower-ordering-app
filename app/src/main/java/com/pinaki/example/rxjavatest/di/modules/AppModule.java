package com.pinaki.example.rxjavatest.di.modules;

import com.pinaki.example.rxjavatest.FlowerShoppingApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    FlowerShoppingApp app;

    public AppModule(FlowerShoppingApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    FlowerShoppingApp providesApplication() {
        return app;
    }
}
