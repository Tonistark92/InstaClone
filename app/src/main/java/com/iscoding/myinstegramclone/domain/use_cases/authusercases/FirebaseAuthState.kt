package com.iscoding.myinstegramclone.domain.use_cases.authusercases

import com.iscoding.myinstegramclone.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseAuthState @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke() = repository.getFirebaseAuthState()

}