package com.example.mobilesubscriber.data.repository

import android.util.Log
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.mobilesubscriber.data.dao.SubscriberDao
import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.data.model.SubscriberModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class SubscriberRepositoryImpl(
    private val dao: SubscriberDao
): SubscriberRepository {
    override fun getSubscribers(): Flow<List<Subscriber>> {
        return dao.getSubscribers()
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertSubscriber(subscriber: Subscriber) {
        subscriber.toString()
        Log.d("SAVING SUBSCRIBER TO DATABASE...........", subscriber.toString())
        return dao.insertSubscriber(subscriber)
    }

    override suspend fun getSubscriberById(id: UUID): Subscriber? {
        Log.d("RETRIEVING SUBSCRIBER FROM DATABASE WITH ID ......................", id.toString())
        return dao.getSubscriberById(id)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertSubscriberList(subscribers: List<Subscriber>) {
        Log.d("SAVING LIST OF SUBSCRIBERS FROM WEB TO DATABASE...........", subscribers.toString())
        dao.insertSubscribers(subscribers)
    }

}