package com.example.mobilesubscriber.screens.authentication.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilesubscriber.R
import com.example.mobilesubscriber.screens.authentication.AuthenticationViewModel
import com.example.mobilesubscriber.util.Screen
import kotlinx.coroutines.launch


@Composable
fun SignInScreen(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
){
    val googleSignInState = authenticationViewModel.googleState.value
    var email by rememberSaveable{ mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = authenticationViewModel.signInState.collectAsState(initial = null)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.login),
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(0.5f)
            ) {
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
                            authenticationViewModel.loginUser(email,password)
                        }
                    },

                ) {
                    androidx.compose.material3.Text(text = "Login")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (googleSignInState.isLoading){
                        CircularProgressIndicator()
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.clickable {
                         navController.navigate(Screen.SignUpScreen.route)
                        },
                        text = "New User? Click to Register",
                    )
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
                navController.navigate(Screen.SubscribersScreen.route)
//                navController.navigate(Screen.RetrofitScreen.route)
            }
        }
    }

    LaunchedEffect(key1 = googleSignInState.isSuccess){
        scope.launch {
            if (googleSignInState.isSuccess != null){
                Toast.makeText(
                    context,
                    "Successfully signed in with google",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    LaunchedEffect(key1 = googleSignInState.isError){
        scope.launch {
            if (state.value?.isError?.isNotEmpty() == true){
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myLoginTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelName: String,
    keyboardOptions: KeyboardOptions
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = labelName) },
        keyboardOptions = keyboardOptions,
        modifier = modifier.fillMaxWidth()
    )

}

