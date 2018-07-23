package com.pinaki.example.rxjavatest.di.components;

import com.pinaki.example.rxjavatest.data.FlowerRepository;
import com.pinaki.example.rxjavatest.data.remote.RemoteData;
import com.pinaki.example.rxjavatest.data.remote.RemoteDataImpl;
import com.pinaki.example.rxjavatest.di.modules.AppModule;
import com.pinaki.example.rxjavatest.di.modules.HttpModule;
import com.pinaki.example.rxjavatest.di.modules.RemoteDataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class, RemoteDataModule.class})
public interface RemoteDataComponent {

    void inject(FlowerRepository repository);

    RemoteData providesRemoteData();

}
