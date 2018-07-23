package com.pinaki.example.rxjavatest.di.modules;

import dagger.Module;
import dagger.Provides;
import com.pinaki.example.rxjavatest.data.remote.RemoteData;
import com.pinaki.example.rxjavatest.data.remote.RemoteDataImpl;
import com.pinaki.example.rxjavatest.data.remote.retrofit.FlowerService;

/**
 * Created by pinaki93 on 19/07/18.
 */

@Module
public class RemoteDataModule {

    @Provides
    RemoteData providesRemoteData(FlowerService flowerService) {
        return new RemoteDataImpl(flowerService);
    }
}
