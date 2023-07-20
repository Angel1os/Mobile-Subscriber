package com.example.mobilesubscriber.services

import com.example.mobilesubscriber.data.model.Post
import com.example.mobilesubscriber.data.model.SubscriberModel


class SubscriberServiceRepository {

    private val apiInterface = RetrofitInstance.apiInterface

    suspend fun getPosts(): List<Post>{
        return apiInterface.getPosts()
    }

    suspend fun getSubscribers(): List<SubscriberModel>{
        return apiInterface.getSubscribers()
    }

    suspend fun insertSubscriber(subscriber: SubscriberModel) {
        return apiInterface.insertSubscriber(subscriber)
    }
}