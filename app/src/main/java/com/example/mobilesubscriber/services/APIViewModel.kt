package com.example.mobilesubscriber.services

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesubscriber.data.model.Post
import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.data.repository.SubscriberRepository
import com.example.mobilesubscriber.data.model.SubscriberModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class APIViewModel @Inject  constructor(
    private val repository: SubscriberRepository
) : ViewModel() {

//    private val addSubscriberRepository: SubscriberRepository

//    private val repository = PostRepository()

    private val subscriberServiceRepository = SubscriberServiceRepository()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _subscribers = MutableLiveData<List<SubscriberModel>>()
    val subscribers: LiveData<List<SubscriberModel>> = _subscribers

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val posts = subscriberServiceRepository.getPosts()
                _posts.value = posts
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

     fun fetchSubscribers(){
        viewModelScope.launch {
            try {
                val subscribers = subscriberServiceRepository.getSubscribers()
                _subscribers.value = subscribers
                Log.d("SUBSCRIBERS FROM WEB......................", subscribers.toString())
                print(subscribers.toString())

                val subscribersFromWeb: MutableList<Subscriber> = mutableListOf()
                subscribers.map { subscriber ->

                    val subscriberFromWeb  = Subscriber(
                        id = subscriber.id.toString(),
                        name = subscriber.name,
                        email = subscriber.email,
                        doB = subscriber.doB,
                        contact = subscriber.contact,
                        location = subscriber.location,
                        status = subscriber.status,
                        createdAt = subscriber.createdAt,
                    )
                    repository.insertSubscriber(subscriberFromWeb)
                    subscribersFromWeb.add(subscriberFromWeb)

                }
//                repository.insertSubscriberList(subscribersFromWeb)
            } catch (e: Exception) {
                print(e.message)
            }
        }

    }

    fun insertSubscriber(subscriber: SubscriberModel){
        viewModelScope.launch {
            subscriberServiceRepository.insertSubscriber(subscriber)
        }
    }


}