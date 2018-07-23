package com.pinaki.example.rxjavatest.data.remote

import com.pinaki.example.rxjavatest.model.remote.GetFlowerResponse
import io.reactivex.Flowable

/**
 * Created by pinaki93 on 05/07/18.
 */
public interface RemoteData {
    fun getFlowers(): Flowable<List<GetFlowerResponse>>
}