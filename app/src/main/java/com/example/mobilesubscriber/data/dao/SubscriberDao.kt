package com.example.mobilesubscriber.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobilesubscriber.data.model.Subscriber
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriberDao{
    @Query("SELECT * FROM subscriber")
    fun getSubscribers(): Flow<List<Subscriber>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscriber(subscriber: Subscriber)

    @Query("SELECT * FROM subscriber WHERE id =:id")
    suspend fun getSubscriberById(id: Int): Subscriber?
}