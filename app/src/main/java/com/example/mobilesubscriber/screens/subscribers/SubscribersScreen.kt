package com.example.mobilesubscriber.screens.subscribers

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobilesubscriber.R
import com.example.mobilesubscriber.data.model.Subscriber
import com.example.mobilesubscriber.screens.subscribers.components.SubscriberItem
import com.example.mobilesubscriber.screens.util.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SubscribersScreen(
    navController: NavController,
    viewModel: SubscribersViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.subscribers_screen_title),
                    )
                },
                actions = {
                    subscribersAppBar {}
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.SubscriberFormScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add note"
                    )
                }
        },

        scaffoldState = scaffoldState
    ) {

        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.subscribers){item: Subscriber ->
                SubscriberItem(
                    subscriber = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            //Navigate To Subscriber Form and fill with Details
                            navController.navigate(Screen.SubscriberFormScreen.route +
                                    "?subscriberId=${item.id}"
                            )
                        }
                )
            }
        }
    }
}

@Composable
fun subscribersAppBar(
    onMenuClicked: () -> Unit,
) {

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.subscribers_screen_title),
            )
        },
        actions = {
            AppBarAction(
                onMenuClicked = onMenuClicked
            )
        }
    )

}

@Composable
fun AppBarAction(onMenuClicked: () -> Unit) {
    IconButton(
        onClick = { onMenuClicked() }
    ) {
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = stringResource(id = R.string.subscribers_screen_title),
        )
    }
}

@Composable
@Preview
private fun AppBarPreview(){
    subscribersAppBar(
        onMenuClicked = {}
    )
}



