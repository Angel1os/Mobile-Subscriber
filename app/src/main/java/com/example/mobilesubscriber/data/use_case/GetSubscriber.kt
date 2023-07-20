package com.example.mobilesubscriber.data.use_case

import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.data.repository.SubscriberRepository
import java.util.UUID

class GetSubscriber(
    private val repository: SubscriberRepository
) {
    suspend operator fun invoke(id: UUID): Subscriber? {
        return repository.getSubscriberById(id)
    }
}