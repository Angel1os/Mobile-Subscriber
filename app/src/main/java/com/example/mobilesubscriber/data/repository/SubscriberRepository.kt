package com.example.mobilesubscriber.data.repository

import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.data.model.SubscriberModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface SubscriberRepository{

    fun getSubscribers(): Flow<List<Subscriber>>

    suspend fun insertSubscriber(subscriber: Subscriber)

    suspend fun getSubscriberById(id: UUID): Subscriber?

    suspend fun insertSubscriberList(subscribers: List<Subscriber>)
}