package com.example.mobilesubscriber.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime
import java.util.Date
import java.util.UUID

@Entity
data class Subscriber(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val contact: String,
    val doB: String,
    val location: String,
    val status: String,
    val createdAt: String?
)

class InvalidSubscriberException(message: String): Exception(message)