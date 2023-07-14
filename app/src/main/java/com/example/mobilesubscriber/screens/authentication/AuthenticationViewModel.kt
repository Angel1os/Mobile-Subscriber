package com.example.mobilesubscriber.screens.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesubscriber.data.model.Resource
import com.example.mobilesubscriber.data.repository.AuthRepository
import com.example.mobilesubscriber.screens.authentication.signin.GoogleSignState
import com.example.mobilesubscriber.screens.authentication.signin.SignInState
import com.example.mobilesubscriber.screens.authentication.signup.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    private val _googleState = mutableStateOf(GoogleSignState())
    val googleState: State<GoogleSignState> = _googleState

    private val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect{
                result ->
            when (result){
                is Resource.Success ->{
                    _signInState.send(SignInState(isSuccess = "Successfully signed in"))
                }

                is Resource.Loading ->{
                    _signInState.send(SignInState(isLoading = true))
                }

                is Resource.Error ->{
                    _signInState.send(SignInState(isError = result.message.toString()))
                }
            }
        }
    }

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        repository.registerUser(email, password).collect{
            result ->
            when (result){
                is Resource.Success ->{
                    _signUpState.send(SignUpState(isSuccess = "Sign Up Success"))

                }

                is Resource.Loading ->{
                    _signUpState.send(SignUpState(isLoading = true))

                }
                is Resource.Error ->{
                    _signUpState.send(SignUpState(isError = result.message))
                }
            }
        }
    }
}