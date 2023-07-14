package com.example.mobilesubscriber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilesubscriber.screens.add_subscribers.AddSubscriberScreen
import com.example.mobilesubscriber.screens.authentication.AuthenticationViewModel
import com.example.mobilesubscriber.screens.authentication.GoogleAuthUiClient
import com.example.mobilesubscriber.screens.authentication.signin.SignInScreen
import com.example.mobilesubscriber.screens.authentication.signup.SignUpScreen
import com.example.mobilesubscriber.screens.subscribers.SubscribersScreen
import com.example.mobilesubscriber.ui.theme.MobileSubscriberTheme
import com.example.mobilesubscriber.screens.util.Screen
import com.example.mobilesubscriber.services.APIViewModel
import com.example.mobilesubscriber.services.RetrofitScreen
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authenticationViewModel: AuthenticationViewModel by viewModels()

    private val retrofitAPIViewModel: APIViewModel by viewModels()


    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileSubscriberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
//                        startDestination = Screen.SubscribersScreen.route
                        startDestination = Screen.SignInScreen.route
                    ){

                        composable(route = Screen.SignInScreen.route){
                            SignInScreen(
                                navController = navController,
                                authenticationViewModel = authenticationViewModel
                            )
                        }

                        composable(route = Screen.SignUpScreen.route){
                            SignUpScreen(
                                navController = navController,
                                authenticationViewModel = authenticationViewModel
                            )
                        }

                        composable(route = Screen.RetrofitScreen.route){
                            RetrofitScreen(
                                viewModel = retrofitAPIViewModel
                            )
                        }

                        composable(route = Screen.SubscribersScreen.route){
                            SubscribersScreen(
                                navController = navController,
                            )
                        }

                        composable(
                                route = "${Screen.SubscriberFormScreen.route}?subscriberId={subscriberId}",
                                arguments = listOf(
                                    navArgument(name = "subscriberId") {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    }
                                )
                            ) {
                                AddSubscriberScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileSubscriberTheme {
        Greeting("Android")
    }
}