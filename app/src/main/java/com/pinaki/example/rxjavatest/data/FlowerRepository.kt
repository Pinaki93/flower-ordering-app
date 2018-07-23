package com.pinaki.example.rxjavatest.data

import com.pinaki.example.rxjavatest.data.remote.RemoteData
import com.pinaki.example.rxjavatest.di.ComponentFactory
import com.pinaki.example.rxjavatest.model.remote.GetFlowerResponse
import io.reactivex.Flowable
import javax.inject.Inject

class FlowerRepository{

    @Inject
    lateinit var remoteDataSource: RemoteData

    init {
        ComponentFactory.remoteDataComponent().inject(this)
    }


    fun getFlowers(): Flowable<List<GetFlowerResponse>> {
        return remoteDataSource.getFlowers()
    }
}