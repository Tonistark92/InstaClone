package com.iscoding.myinstegramclone.presentation.main.posts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.iscoding.myinstegramclone.domain.model.Post
import com.iscoding.myinstegramclone.domain.use_cases.postUseCases.PostsUseCase
import com.iscoding.myinstegramclone.util.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postsUseCase: PostsUseCase,
    private val auth: FirebaseAuth
) : ViewModel() {
    private val _postData = mutableStateOf<MyResponse<List<Post>>>(
        MyResponse.Loading(true)
    )
    val postData: State<MyResponse<List<Post>>> = _postData

    private val _uploadPostData = mutableStateOf<MyResponse<Boolean>>(
        MyResponse.Loading(true)
    )
    val uploadPostData: State<MyResponse<Boolean>> = _uploadPostData

    fun uploadPost (
        postId: String,
        postImage: String,
        postDescription: String,
        time: Long,
        userId: String,
        userName: String,
        userImage: String
    ){
        viewModelScope.launch {
            postsUseCase.uploadPost(
                postId = postId,
                postImage = postImage,
                postDescription = postDescription,
                time = time,
                userId = userId,
                userName = userName,
                userImage = userImage

            ).collect{
                _uploadPostData.value =it
            }


        }
    }
    fun  getAllPosts (){
        viewModelScope.launch {
            postsUseCase.getAllPosts(auth.uid.toString()).collect{
                _postData.value = it
            }
        }
    }
}