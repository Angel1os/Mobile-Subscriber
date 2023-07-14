package com.example.mobilesubscriber.screens.authentication.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilesubscriber.screens.authentication.AuthenticationViewModel
import com.example.mobilesubscriber.screens.authentication.signin.myLoginTextField
import com.example.mobilesubscriber.screens.util.Screen
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = authenticationViewModel.signUpState.collectAsState(initial = null)

    Box(
        modifier = Modifier
            .fillMaxSize(),

        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            myLoginTextField(
                modifier = Modifier,
                value = email,
                onValueChange = { email = it },
                labelName = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            myLoginTextField(
                modifier = Modifier,
                value = password,
                onValueChange = { password = it },
                labelName = "Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Button(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                onClick = {
                    scope.launch {
                        authenticationViewModel.registerUser(email,password)
                    }
                },

                ) {
                Text(text = "Sign Up")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                if (state.value?.isLoading == true){
                    CircularProgressIndicator()
                }
            }
        }

    }

    LaunchedEffect(key1 = state.value?.isSuccess){
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true){
                val success = state.value?.isSuccess
                Toast.makeText(
                    context,
                    "$success",
                    Toast.LENGTH_LONG
                ).show()
                navController.navigate(Screen.SignInScreen.route)
            }
        }
    }

    LaunchedEffect(key1 = state.value?.isError) {
        scope.launch {
            if (state.value?.isError?.isNotBlank() == true) {
                val error = state.value?.isError
                Toast.makeText(
                    context,
                    "$error",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}