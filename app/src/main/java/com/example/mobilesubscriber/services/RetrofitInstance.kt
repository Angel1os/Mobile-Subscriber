package com.example.mobilesubscriber.services

import com.example.mobilesubscriber.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val base_url = BASE_URL

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: APIInterface by lazy {
        retrofit.create(APIInterface::class.java)
    }
}