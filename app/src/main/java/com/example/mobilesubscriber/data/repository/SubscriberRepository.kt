package com.example.mobilesubscriber.data.repository

import com.example.mobilesubscriber.data.model.Subscriber
import kotlinx.coroutines.flow.Flow

interface SubscriberRepository{

    fun getSubscribers(): Flow<List<Subscriber>>

    suspend fun insertSubscriber(subscriber: Subscriber)

    suspend fun getSubscriberById(id: Int): Subscriber?
}