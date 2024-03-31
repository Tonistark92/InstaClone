package com.iscoding.myinstegramclone.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.firestore.toObjects
import com.iscoding.myinstegramclone.domain.model.Post
import com.iscoding.myinstegramclone.domain.repository.postRepository
import com.iscoding.myinstegramclone.util.MyResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class postRepositoryImp @Inject constructor(
    private val fireBaseFireStore: FirebaseFirestore
) : postRepository {
    var postUploadedSuccessful = false
    override fun getAllPosts(userId: String): Flow<MyResponse<List<Post>>> = callbackFlow {
        trySend(MyResponse.Loading(isLoading = true))
        val snapshotListener =
            fireBaseFireStore.collection("posts").whereNotEqualTo("userId", userId)
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val postList = snapshot.toObjects(Post::class.java)
                        MyResponse.Success(data = postList)
                    } else {
                        MyResponse.Error(e?.message ?: e.toString(), data = null)
                    }
                    trySend(response as MyResponse<List<Post>>)

                }
        trySend(MyResponse.Loading(isLoading = false))

        awaitClose {
            snapshotListener.remove()
        }

    }

    override fun uploadPosts(
        postId: String,
        postImage: String,
        postDescription: String,
        time: Long,
        userId: String,
        userName: String,
        userImage: String
    ): Flow<MyResponse<Boolean>> = flow {
        postUploadedSuccessful = false
        try {
            val postId = fireBaseFireStore.collection("posts").document().id
            val post = Post(
                postId= postId,
                postImage=postImage,
                postDescription=postDescription,
                time=time,
                userId=userId,
                userName=userName,
                userImage=userImage
            )
            fireBaseFireStore.collection("posts").document(postId).set(post).addOnSuccessListener {
                postUploadedSuccessful =true

            }.await()
            if (postUploadedSuccessful){
                emit(MyResponse.Success(true))
            }else{
                emit(MyResponse.Error("did upload the post ", false))
            }
        } catch (e: Exception) {
            emit(MyResponse.Error(e.localizedMessage?: "Error in Post Uploading Unknown"))
        }
    }
}