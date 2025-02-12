package com.iscoding.myinstegramclone.domain.use_cases.authusercases

import com.iscoding.myinstegramclone.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignUp @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke (email :String, password:String,username:String) =repository.firebaseSignUp(email, password,username)

}