package com.example.mobilesubscriber.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subscriber(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val email: String,
    val contact: String,
    val doB: String,
    val location: String,
    val status: String,
    val timestamp: Long
)

class InvalidSubscriberException(message: String): Exception(message)