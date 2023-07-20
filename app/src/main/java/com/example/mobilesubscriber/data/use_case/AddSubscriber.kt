package com.example.mobilesubscriber.data.use_case

import com.example.mobilesubscriber.data.model.InvalidSubscriberException
import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.data.repository.SubscriberRepository
import com.example.mobilesubscriber.services.APIViewModel

class AddSubscriber(
    private val repository: SubscriberRepository,
) {
    suspend operator fun invoke(subscriber: Subscriber){
        if (subscriber.name.isBlank()){
            throw InvalidSubscriberException("The name of the subscriber can't be empty.")
        }
//        repository.insertSubscriber(subscriber)

    }
}