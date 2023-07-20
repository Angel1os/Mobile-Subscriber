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
    val subscribers by viewModel.subscribers.observeAsState(emptyList())

    LaunchedEffect(Unit) {
//        viewModel.fetchPosts()
        viewModel.fetchSubscribers()
    }

//    Column {
//        if (posts.isEmpty()){
//            Text(text = "Nothing dey ooo")
//        } else {
//            posts.forEach{ card->
//                Log.d("MY POST____________________",card.title)
//                Text(text = "${card.title}")
//            }
//
//        }

        Column {
            if (subscribers.isEmpty()) {
                Text(text = "Nothing dey ooo")
            } else {
                subscribers.forEach { subscriber ->
                    Log.d("SUBSCRIBER____________________", subscriber.name)
                    Text(text = "${subscriber.name}")
                    Text(text = subscriber.email)
                    Text(text = subscriber.location)
                    Text(text = subscriber.contact)
                    Text(text = subscriber.doB)
                    Text(text = subscriber.status)
                    Text(text = subscriber.createdAt)
                }
            }
        }
//    }
}