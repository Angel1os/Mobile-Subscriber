package com.example.mobilesubscriber.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobilesubscriber.data.dao.SubscriberDao

import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.data.repository.Converters

@Database(
    entities = [Subscriber::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class SubscriberDatabase: RoomDatabase() {
    abstract val subscriberDao: SubscriberDao

    companion object {
        const val DATABASE_NAME = "subscribers_db"
    }
}