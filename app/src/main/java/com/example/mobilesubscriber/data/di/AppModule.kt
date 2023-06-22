package com.example.mobilesubscriber.data.di

import android.app.Application
import androidx.room.Room
import com.example.mobilesubscriber.data.SubscriberDatabase
import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.data.repository.SubscriberRepository
import com.example.mobilesubscriber.data.repository.SubscriberRepositoryImpl
import com.example.mobilesubscriber.data.use_case.AddSubscriber
import com.example.mobilesubscriber.data.use_case.GetSubscriber
import com.example.mobilesubscriber.data.use_case.GetSubscribers
import com.example.mobilesubscriber.data.use_case.SubscriberUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSubscriberDatabase(app: Application): SubscriberDatabase {
        return Room.databaseBuilder(
            app,
            SubscriberDatabase::class.java,
            SubscriberDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideSubscriberRepository(db: SubscriberDatabase): SubscriberRepository{
        return SubscriberRepositoryImpl(db.subscriberDao)
    }

    @Provides
    @Singleton
    fun providerSubscriberUseCases(repository: SubscriberRepository): SubscriberUseCases{
        return SubscriberUseCases(
            getSubscribers = GetSubscribers(repository),
            addSubscriber = AddSubscriber(repository),
            getSubscriber = GetSubscriber(repository)
        )
    }

}