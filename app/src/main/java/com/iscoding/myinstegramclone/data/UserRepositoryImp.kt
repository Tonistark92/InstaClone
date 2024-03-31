package com.iscoding.myinstegramclone.data

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.iscoding.myinstegramclone.domain.model.User
import com.iscoding.myinstegramclone.domain.repository.UserRepository
import com.iscoding.myinstegramclone.util.MyResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {
    private var isOperationSuccessful: Boolean = false
    override fun getUserDetails(userId: String): Flow<MyResponse<User>> = callbackFlow {
        val snapShotListener = firebaseFirestore.collection("users")
            .document(userId)
            .addSnapshotListener { shot, error ->
                val response = if (shot != null) {
                    val userInfo = shot.toObject(User::class.java)
                    MyResponse.Success(userInfo)
                } else {
                    MyResponse.Error(error?.message.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapShotListener.remove()
        }
    }

    override fun setUserDetails(
        userid: String,
        name: String,
        userName: String,
        bio: String,
        siteUrl: String
    ): Flow<MyResponse<Boolean>> = flow {
        try {
            isOperationSuccessful = false
            val userObj = mutableMapOf<String, String>()
            userObj["name"] = name
            userObj["userName"] = name
            userObj["bio"] = name
            userObj["siteUrl"] = name
            firebaseFirestore.collection("users").document(userid)
                .update(userObj as Map<String, Any>).addOnSuccessListener {

            }.await()
            if (isOperationSuccessful) {
                emit(MyResponse.Success(isOperationSuccessful))
            } else {
                emit(MyResponse.Error("Couldnt set user details idk why", data = null))
            }


        } catch (e: Exception) {
            MyResponse.Error(e.localizedMessage.toString(), data = null)
        }
    }
}