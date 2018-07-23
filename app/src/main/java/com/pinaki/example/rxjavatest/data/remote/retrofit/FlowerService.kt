package com.pinaki.example.rxjavatest.data.remote.retrofit

import com.pinaki.example.rxjavatest.model.remote.GetFlowerResponse
import io.reactivex.Flowable
import retrofit2.http.GET

public interface FlowerService {

    @GET("feeds/flowers.json")
    fun getAllFlowers(): Flowable<List<GetFlowerResponse>>

}