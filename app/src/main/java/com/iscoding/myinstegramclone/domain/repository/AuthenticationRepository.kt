package com.iscoding.myinstegramclone.domain.repository

import com.iscoding.myinstegramclone.util.MyResponse
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {


    fun isUserAuthanticatedInFirebase():Boolean

    fun getFirebaseAuthState(): Flow<Boolean>

    fun firebaseSignIn (email :String ,password:String):Flow<MyResponse<Boolean>>

    fun firebaseSignOut () :Flow<MyResponse<Boolean>>

    fun firebaseSignUp(email :String ,Password :String,username:String) :Flow<MyResponse<Boolean>>
}