package com.example.mobilesubscriber.data.use_case

import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.data.repository.SubscriberRepository

class GetSubscriber(
    private val repository: SubscriberRepository
) {
    suspend operator fun invoke(id: Int): Subscriber? {
        return repository.getSubscriberById(id)
    }
}