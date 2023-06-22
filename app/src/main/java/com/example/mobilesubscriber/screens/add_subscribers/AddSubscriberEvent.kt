package com.example.mobilesubscriber.screens.add_subscribers

sealed class AddSubscriberEvent {
    data class EnteredSubscriberName(val value: String): AddSubscriberEvent()
    data class EnteredSubscriberEmail(val value: String): AddSubscriberEvent()
    data class EnteredSubscriberContact(val value: String): AddSubscriberEvent()
    data class EnteredSubscriberDob(val value: String): AddSubscriberEvent()
    data class EnteredSubscriberLocation(val value: String): AddSubscriberEvent()
    data class EnteredSubscriberStatus(val value: String): AddSubscriberEvent()
    object SaveSubscriber: AddSubscriberEvent()

}