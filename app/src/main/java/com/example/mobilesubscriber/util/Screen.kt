package com.example.mobilesubscriber.util

sealed class Screen(val route:String) {
    object SignInScreen: Screen("sign_in")

    object SignUpScreen: Screen("sign_up")

    object RetrofitScreen: Screen("retrofit_screen")

    object SubscribersScreen: Screen("subscribers_list_screen")

    object SubscriberFormScreen: Screen("subscribers_form_screen")

}