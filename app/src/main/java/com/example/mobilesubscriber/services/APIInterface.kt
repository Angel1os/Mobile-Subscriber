package com.example.mobilesubscriber.services

import com.example.mobilesubscriber.data.model.Post
import retrofit2.http.GET

interface APIInterface {

    @GET("/posts")
    suspend fun getPosts(): List<Post>

}