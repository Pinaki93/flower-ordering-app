package com.pinaki.example.rxjavatest.data.remote

import com.pinaki.example.rxjavatest.data.remote.retrofit.FlowerService
import com.pinaki.example.rxjavatest.model.remote.GetFlowerResponse
import io.reactivex.Flowable

public class RemoteDataImpl(private var flowerService: FlowerService) : RemoteData {

    override fun getFlowers(): Flowable<List<GetFlowerResponse>> {
        return flowerService.getAllFlowers()
    }
}