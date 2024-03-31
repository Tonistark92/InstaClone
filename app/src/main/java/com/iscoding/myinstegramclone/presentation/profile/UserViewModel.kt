package com.iscoding.myinstegramclone.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.iscoding.myinstegramclone.domain.model.User
import com.iscoding.myinstegramclone.domain.use_cases.userusercases.UserUseCases
import com.iscoding.myinstegramclone.util.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userUseCases: UserUseCases
) : ViewModel() {
    init {
        getUserInfo()
    }
    private val userId = firebaseAuth.currentUser?.uid
    private val _getUserData = mutableStateOf<MyResponse<User>>(MyResponse.Success(null))
    val getUserData: State<MyResponse<User>> = _getUserData

    private val _setUserData = mutableStateOf<MyResponse<Boolean>>(MyResponse.Success(null))
    val setUserData: State<MyResponse<Boolean>> = _setUserData

    fun getUserInfo() {
//        if (userId != null) {
            viewModelScope.launch {
                userUseCases.getUserDetails("VmI1VCj9FjVUz0GIGY0gIXN5xrx1").collect {
                    _getUserData.value = it
                }
            }
//        }
    }

    fun setUserData(
        name: String,
        userName: String,
        bio: String,
        siteUrl: String
    ) {
        if (userId != null) {
            viewModelScope.launch {
                userUseCases.setUserDetails(
                    name = name,
                    userName = userName,
                    userid = userId,
                    bio = bio,
                    siteUrl = siteUrl
                ).collect{
                    _setUserData.value = it
                }
            }
        }
    }
}