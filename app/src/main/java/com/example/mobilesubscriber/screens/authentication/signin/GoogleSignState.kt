package com.example.mobilesubscriber.screens.authentication.signin

import com.google.firebase.auth.AuthResult

class GoogleSignState (
    val isLoading: Boolean = false,
    val isSuccess: AuthResult? = null,
    val isError: String? = ""
)