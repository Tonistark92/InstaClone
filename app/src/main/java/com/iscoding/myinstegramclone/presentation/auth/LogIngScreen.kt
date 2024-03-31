package com.iscoding.myinstegramclone.presentation.auth

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iscoding.myinstegramclone.R
import com.iscoding.myinstegramclone.domain.navigation.Graphs
import com.iscoding.myinstegramclone.util.MyResponse
import com.iscoding.myinstegramclone.domain.navigation.Screens
import com.iscoding.myinstegramclone.ui.theme.AccentColor
import com.iscoding.myinstegramclone.ui.theme.IconDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(navController: NavController, authViewModel: AuthenticationViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(
                    rememberScrollState(),
                )
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val orentaion= LocalConfiguration.current.orientation
            val text = Configuration.ORIENTATION_LANDSCAPE
            val email = remember {
                mutableStateOf("")
            }
            val password = remember {
                mutableStateOf("")
            }

            val showPassword = remember {
                mutableStateOf(false)
            }
            val showPasswordError = remember {
                mutableStateOf(false)
            }
            val showEmailError = remember {
                mutableStateOf(false)
            }
            //Image

            Image(painter = painterResource(id = R.drawable.ic_instagram), contentDescription = "")
            Spacer(modifier = Modifier.size(40.dp))


            OutlinedTextField(
                value = email.value, onValueChange = {
                    email.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Enter Your Email") },
                isError = showEmailError.value
            )
            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Enter Your Password") },
                visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon =
                        if (showPassword.value) Icons.Default.VisibilityOff else Icons.Default.Visibility
                    IconButton(onClick = { showPassword.value = !showPassword.value }) {
                        Icon(icon, contentDescription = null)
                    }
                },
                isError = showPasswordError.value,

                )
            Spacer(modifier = Modifier.size(30.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = AccentColor),
                shape = RoundedCornerShape(size = 5.dp),
                onClick = {
                    navController.navigate(Graphs.PAGES) {
                        popUpTo(Screens.ProfileScreen.route) {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(
                    text = "LogIn",
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 7.dp)
                )
//                if (password.value.isEmpty()){
//                    showPasswordError.value =true
//                }
//                if (email.value.isEmpty()){
//                    showEmailError.value= true
//                }
//                when (val response = authViewModel._signInState.value) {
//                    is MyResponse.Error -> Toast.makeText(
//                        LocalContext.current,
//                        "There Is Error " + response.message,
//                        Toast.LENGTH_LONG
//                    ).show()
//
//                    is MyResponse.Loading -> {
////                        CircularProgressIndicator(color = IconDark)
//                    }
//
//                    is MyResponse.Success -> {
//                        LaunchedEffect(key1 = true) {
//                            navController.navigate(Graphs.PAGES) {
//                                popUpTo(Screens.ProfileScreen.route) {
//                                    inclusive = true
//                                }
//                            }
//                        }
//                    }


//                }
            }
            Spacer(modifier = Modifier.height(20.dp))
//            Text(
//                text = "New ? Sign Up Now ",
//                color = Color.Blue,
//                modifier = Modifier
//                    .padding(8.dp)
//                    .clickable {
//                        navController.navigate(Screens.SignUpScreen.route) {
//                            launchSingleTop = true
//                        }
//                    })


        }

    }

}