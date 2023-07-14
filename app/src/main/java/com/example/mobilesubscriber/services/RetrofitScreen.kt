package com.example.mobilesubscriber.services

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun RetrofitScreen(
    viewModel: APIViewModel
) {
    val posts by viewModel.posts.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchPosts()
    }

    Column {
        if (posts.isEmpty()) {
            Text(text = "Nothing dey ooo")
        } else {
            posts.forEach { post ->
                Log.d("MY POST____________________", post.title)
                Text(text = "${post.title}")
            }
        }
    }
}