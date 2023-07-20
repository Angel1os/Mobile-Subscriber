package com.example.mobilesubscriber.services

import com.example.mobilesubscriber.data.model.Post

class PostRepository {

    private val apiInterface = RetrofitInstance.apiInterface

    suspend fun getPosts(): List<Post> {
        return apiInterface.getPosts()
    }
}