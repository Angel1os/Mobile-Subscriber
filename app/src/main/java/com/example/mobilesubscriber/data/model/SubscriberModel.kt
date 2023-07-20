package com.example.mobilesubscriber.data.model

import java.time.ZonedDateTime
import java.util.Date
import java.util.UUID

data class SubscriberModel(
    val id: UUID,
    val name: String,
    val email: String,
    val contact: String,
    val doB: String,
    val location: String,
    val status: String,
    val createdAt: String
)
