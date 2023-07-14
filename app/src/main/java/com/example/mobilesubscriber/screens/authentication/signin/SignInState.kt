package com.example.mobilesubscriber.screens.authentication.signin

data class SignInState (
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)