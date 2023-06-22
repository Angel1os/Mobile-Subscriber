package com.example.mobilesubscriber.data.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.mobilesubscriber.data.dao.SubscriberDao
import com.example.mobilesubscriber.data.model.Subscriber
import kotlinx.coroutines.flow.Flow

class SubscriberRepositoryImpl(
    private val dao: SubscriberDao
): SubscriberRepository {
    override fun getSubscribers(): Flow<List<Subscriber>> {
        return dao.getSubscribers()
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertSubscriber(subscriber: Subscriber) {
        subscriber.toString()
        return dao.insertSubscriber(subscriber)
    }

    override suspend fun getSubscriberById(id: Int): Subscriber? {
        return dao.getSubscriberById(id)
    }

}