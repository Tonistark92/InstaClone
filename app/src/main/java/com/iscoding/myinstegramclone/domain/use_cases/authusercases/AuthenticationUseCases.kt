package com.iscoding.myinstegramclone.domain.use_cases.authusercases

data class AuthenticationUseCases (
    val isUserAuthenticated: IsUserAuthenticated,
    val firebaseSignUp: FirebaseSignUp,
    val firebaseSignIn: FirebaseSignIn,
    val firebaseSignOut: FirebaseSignOut,
    val firebaseState: FirebaseAuthState
)