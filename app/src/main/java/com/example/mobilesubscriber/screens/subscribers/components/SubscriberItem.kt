package com.example.mobilesubscriber.screens.subscribers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilesubscriber.data.model.Subscriber
import kotlin.random.Random


@Composable
fun SubscriberItem(
    subscriber: Subscriber,
    modifier: Modifier,
//    userData: UserData?
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(color = Color.White)
            .padding(20.dp, 10.dp, 20.dp, 20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            boxCircle(
                color = generateRandomColor(),
                initial = subscriber.name.take(1).uppercase()
            )
            Column(
                modifier = Modifier
                    .padding(10.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = subscriber.name)
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = subscriber.contact.toString())
//                    if (userData?.username != null){
//                        Text(text = subscriber.contact)
//                    }
                    Text(
                        text = subscriber.status,
                        modifier = Modifier
                            .background(
                                color = generateStatusColor(subscriber.status),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}
@Composable
fun boxCircle(color: Color, initial: String) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = initial,
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.White
            )
        )
    }
}

fun generateRandomColor(): Color {
    val random = Random
    val red = random.nextInt(256)
    val green = random.nextInt(256)
    val blue = random.nextInt(256)
    return Color(red, green, blue)
}

fun generateStatusColor(status: String): Color {
    return if (status == "Postpaid") {
        Color.Green
    } else {
        Color.Blue
    }
}
