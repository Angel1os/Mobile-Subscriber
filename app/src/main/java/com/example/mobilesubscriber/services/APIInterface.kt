package com.example.mobilesubscriber.services

import com.example.mobilesubscriber.data.model.Post
import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.data.model.SubscriberModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("/posts")
    suspend fun getPosts(): List<Post>
    @GET("/api/mobile/subscribers")
    suspend fun getSubscribers(): List<SubscriberModel>
    @POST("/api/mobile/subscribers")
    suspend fun insertSubscriber(@Body subscriber: SubscriberModel)

}