package com.example.mobilesubscriber.screens.util

sealed class Screen(val route:String) {
    object SubscribersScreen: Screen("subscribers_list_screen")

    object SubscriberFormScreen: Screen("subscribers_form_screen")

    fun varWithArgs(arg:String){

    }

}