package com.pinaki.example.rxjavatest.di;

import com.pinaki.example.rxjavatest.BuildConfig;
import com.pinaki.example.rxjavatest.FlowerShoppingApp;
import com.pinaki.example.rxjavatest.di.modules.AppModule;
import com.pinaki.example.rxjavatest.di.modules.HttpModule;
import com.pinaki.example.rxjavatest.di.modules.RemoteDataModule;
import com.pinaki.example.rxjavatest.di.components.RemoteDataComponent;
import com.pinaki.example.rxjavatest.di.components.DaggerRemoteDataComponent;

public class ComponentFactory {

    private static RemoteDataComponent remoteDataComponent;

    public static void initialize(FlowerShoppingApp app) {
        remoteDataComponent = DaggerRemoteDataComponent.builder()
                .httpModule(new HttpModule(BuildConfig.BASE_URL))
                .remoteDataModule(new RemoteDataModule())
                .appModule(new AppModule(app))
                .build();
    }

    public static RemoteDataComponent remoteDataComponent() {
        return remoteDataComponent;
    }
}
