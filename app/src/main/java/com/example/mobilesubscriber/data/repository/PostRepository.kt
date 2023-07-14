package com.example.mobilesubscriber.data.repository

import com.example.mobilesubscriber.data.model.Post
import com.example.mobilesubscriber.services.RetrofitInstance

class PostRepository {

    private val postService = RetrofitInstance.apiInterface

    suspend fun getPosts(): List<Post> {
        return postService.getPosts()
    }
}