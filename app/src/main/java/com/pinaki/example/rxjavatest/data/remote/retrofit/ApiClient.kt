package com.pinaki.example.rxjavatest.data.remote.retrofit

import com.google.gson.GsonBuilder
import com.pinaki.example.rxjavatest.config.getBaseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

public class ApiClient {
    companion object {
        private var instance: Retrofit? = null

        public fun getRetrofit(): Retrofit {
            if (instance == null) {
                initRetrofit()
            }

            return instance!!
        }

        private fun initRetrofit() {
            // initializing okhttp
            var okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor())
                    .build()

            // initializing gson
            val gson = GsonBuilder().setLenient().create()

            instance = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
}