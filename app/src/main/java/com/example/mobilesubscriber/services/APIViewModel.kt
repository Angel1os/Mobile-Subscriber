package com.example.mobilesubscriber.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesubscriber.data.model.Post
import com.example.mobilesubscriber.data.repository.PostRepository
import kotlinx.coroutines.launch


class APIViewModel : ViewModel() {
    private val repository = PostRepository()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val posts = repository.getPosts()
                _posts.value = posts
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }


}