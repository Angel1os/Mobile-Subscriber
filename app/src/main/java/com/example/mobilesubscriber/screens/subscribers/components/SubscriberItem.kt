package com.example.mobilesubscriber.screens.subscribers.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilesubscriber.R
import com.example.mobilesubscriber.data.model.Subscriber

@Composable
fun SubscriberItem(
    subscriber: Subscriber,
    modifier: Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(color = Color.White)
            .padding(20.dp, 10.dp, 20.dp, 20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors =  CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            circleShape()
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
                    Text(
                        text = subscriber.contact
                    )
                    Text(
                        text = subscriber.status,
                        if (subscriber.status == "Postpaid"){
                            Modifier.background(
                                Color.Green.copy(alpha = maxOf(0.2,0.8).toFloat()),
                                shape = RoundedCornerShape(4.dp)
                            )
                        } else {
                            Modifier.background(
                                Color.Blue.copy(alpha = maxOf(0.2,0.8).toFloat()),
                                shape = RoundedCornerShape(4.dp),
                            )
                        },
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White.copy(alpha = maxOf(0.2,0.8).toFloat()),
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun circleShape() {
    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = null,
        modifier = Modifier.clip(CircleShape)
    )
}

//@Composable
//fun boxCircle(){
//    Box(
//
//    )
//}

@Composable
@Preview
private fun SubscriberListItem() {
    SubscriberItem(
        subscriber =
        Subscriber(
            id = null,
            name = "Prince",
            email = "prince@gmail.com",
            contact = "024567799",
            doB = "9th May 2020",
            location = "East Legon",
            status = "Postpaid",
            timestamp = System.currentTimeMillis()
        ), modifier = Modifier
    )
}