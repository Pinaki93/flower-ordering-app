package com.pinaki.example.rxjavatest.data

import com.pinaki.example.rxjavatest.data.remote.RemoteData
import com.pinaki.example.rxjavatest.data.remote.RemoteDataImpl
import com.pinaki.example.rxjavatest.data.remote.retrofit.FlowerService

public class DataSourceFactory {
    companion object {
        public fun getRemoteDataSource(): RemoteData? {
            return null
        }
    }
}