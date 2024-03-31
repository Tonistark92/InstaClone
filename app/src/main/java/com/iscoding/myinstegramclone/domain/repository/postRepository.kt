package com.iscoding.myinstegramclone.domain.repository

import com.iscoding.myinstegramclone.domain.model.Post
import com.iscoding.myinstegramclone.util.MyResponse
import kotlinx.coroutines.flow.Flow

interface postRepository {
    fun getAllPosts(userId:String) : Flow<MyResponse<List<Post>>>
    fun uploadPosts(
        postId :String,
        postImage : String,
        postDescription : String,
        time : Long ,
        userId : String,
        userName : String,
        userImage :String
    ) : Flow<MyResponse<Boolean>>

}