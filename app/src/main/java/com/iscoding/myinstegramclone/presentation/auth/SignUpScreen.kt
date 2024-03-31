package com.iscoding.myinstegramclone.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iscoding.myinstegramclone.util.MyResponse
import com.iscoding.myinstegramclone.domain.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, authViewModel: AuthenticationViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(
                    rememberScrollState(),
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val email = remember {
                mutableStateOf("")
            }
            val password = remember {
                mutableStateOf("")
            }
            val userName = remember {
                mutableStateOf("")
            }
            Text(text = "Instgram LOGO")
            Spacer(modifier = Modifier.size(20.dp))

            Text(
                text = "Sign Un",
                modifier = Modifier.padding(10.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            )
            OutlinedTextField(value = userName.value, onValueChange = {
                userName.value = it
            },
                modifier = Modifier.padding(10.dp),
                label = { Text(text = "Enter Your User Name") })


            OutlinedTextField(value = email.value, onValueChange = {
                email.value = it
            },
                modifier = Modifier.padding(10.dp),
                label = { Text(text = "Enter Your Email") })


            OutlinedTextField(
                value = password.value, onValueChange = {
                    password.value = it
                },
                modifier = Modifier.padding(10.dp),
                label = { Text(text = "Enter Your Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedButton(onClick = {
                authViewModel.signUp(
                    email = email.value,
                    password = password.value,
                    userName = userName.value
                )

            }, modifier = Modifier.padding(8.dp)) {
                Text(text = "Sign In")
                when (val response = authViewModel._signUpState.value) {
                    is MyResponse.Error -> Toast.makeText(
                        LocalContext.current,
                        "There Is Error " + response.message,
                        Toast.LENGTH_LONG
                    ).show()

                    is MyResponse.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                    }

                    is MyResponse.Success -> {
                        LaunchedEffect(key1 = true) {
                            navController.navigate(Screens.FeedsScreen.route) {
                                popUpTo(Screens.LogInScreen.route) {
                                    inclusive = true
                                }
                            }
                        }

                    }
                }
            }
            Text(
                text = "had account ? Sign In Now ",
                color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(Screens.LogInScreen.route) {
                            launchSingleTop = true
                        }
                    })

        }
    }
}