package com.example.mobilesubscriber.data.use_case

import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.data.repository.SubscriberRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSubscribers(
    private val repository: SubscriberRepository
) {
    operator fun invoke(): Flow<List<Subscriber>>{
        return repository.getSubscribers()
//        Later to sort with mapping
    }
}