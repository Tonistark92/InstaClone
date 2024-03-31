package com.iscoding.myinstegramclone.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iscoding.myinstegramclone.domain.use_cases.authusercases.AuthenticationUseCases
import com.iscoding.myinstegramclone.util.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authUseCases: AuthenticationUseCases
) : ViewModel() {
    val isUserAuthenticated
        get() = authUseCases.isUserAuthenticated()

    val _signInState = mutableStateOf<MyResponse<Boolean>>(MyResponse.Loading(false))
    val signIn: State<MyResponse<Boolean>> = _signInState

    val _signUpState = mutableStateOf<MyResponse<Boolean>>(MyResponse.Loading(false))
    val signUp: State<MyResponse<Boolean>> = _signUpState

    val _signOutState = mutableStateOf<MyResponse<Boolean>>(MyResponse.Loading(false))
    val signOut: State<MyResponse<Boolean>> = _signOutState

    val _authState = mutableStateOf<Boolean>(false)
    val authState: State<Boolean> = _authState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authUseCases.firebaseSignIn(email = email, password = password).collect {
                _signInState.value = it
            }
        }
    }

    fun signUp(email: String, password: String, userName: String) {
        viewModelScope.launch {
            authUseCases.firebaseSignUp(email = email, password = password, username = userName)
                .collect {
                    _signUpState.value = it
                }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authUseCases.firebaseSignOut().collect {
                _signOutState.value = it
                if (it == MyResponse.Success(true)) {
                    _signInState.value = MyResponse.Success(false)
                }
            }
        }
    }

    fun getAuthState() {
        viewModelScope.launch {
            authUseCases.firebaseState().collect {
                _authState.value = it
            }
        }
    }
}