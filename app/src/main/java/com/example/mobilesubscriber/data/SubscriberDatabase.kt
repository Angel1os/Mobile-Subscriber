package com.example.mobilesubscriber.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobilesubscriber.data.dao.SubscriberDao

import com.example.mobilesubscriber.data.model.Subscriber

@Database(
    entities = [Subscriber::class],
    version = 1
)
abstract class SubscriberDatabase: RoomDatabase() {
    abstract val subscriberDao: SubscriberDao

    companion object {
        const val DATABASE_NAME = "subscribers_db"
    }
}