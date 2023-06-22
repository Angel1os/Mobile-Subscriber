package com.example.mobilesubscriber.screens.subscribers

import com.example.mobilesubscriber.data.model.Subscriber


data class SubscribersState (
    val subscribers: List<Subscriber> = emptyList()
)