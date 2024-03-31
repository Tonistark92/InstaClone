package com.iscoding.myinstegramclone.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.iscoding.myinstegramclone.domain.model.User
import com.iscoding.myinstegramclone.domain.repository.AuthenticationRepository
import com.iscoding.myinstegramclone.util.Constants
import com.iscoding.myinstegramclone.util.MyResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
    val auth: FirebaseAuth,
    val firestore: FirebaseFirestore
) : AuthenticationRepository {
    var operationSuccess: Boolean = false
    override fun isUserAuthanticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    override fun getFirebaseAuthState(): Flow<Boolean> = callbackFlow {
        val authListener = FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authListener)
        awaitClose {
            auth.removeAuthStateListener(authListener)
        }
    }

    override fun firebaseSignIn(email: String, password: String): Flow<MyResponse<Boolean>> = flow {
        operationSuccess = false
        try {
            emit(MyResponse.Loading(true))
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccess = true
            }.await()
            emit(MyResponse.Success(operationSuccess))

        } catch (e: Exception) {
            emit(MyResponse.Error(message = e.localizedMessage ?: " Error while sign In"))
        }
    }

    override fun firebaseSignOut(): Flow<MyResponse<Boolean>> = flow {
        try {
            emit(MyResponse.Loading(true))
            auth.signOut()
            emit(MyResponse.Success(true))
        } catch (e: Exception) {
            emit(MyResponse.Error(message = e.localizedMessage ?: " Error while sign In"))
        }


    }

    override fun firebaseSignUp(email: String, Password: String,username:String): Flow<MyResponse<Boolean>> = flow {
        operationSuccess = false
        try {
            emit(MyResponse.Loading(true))
            auth.createUserWithEmailAndPassword(email, Password).addOnSuccessListener {
                operationSuccess = true
            }.await()
            if (operationSuccess){
                val userId = auth.currentUser?.uid!!
                val obj = User(userName =username ,id = userId, password =Password, email = email)
                firestore.collection(Constants.COLLECTION_NAME_USERS).document(userId).set(obj).addOnSuccessListener {

                }.await()
                emit(MyResponse.Success(operationSuccess))
            }
            else{
                MyResponse.Success(operationSuccess)
            }
        } catch (e: Exception) {
            emit(MyResponse.Error(message = e.localizedMessage ?: " Error while sign In"))
        }

    }
}