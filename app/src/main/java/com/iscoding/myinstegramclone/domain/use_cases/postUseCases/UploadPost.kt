package com.iscoding.myinstegramclone.domain.use_cases.postUseCases

import com.iscoding.myinstegramclone.domain.repository.postRepository
import javax.inject.Inject

class UploadPost @Inject constructor(
    private val repository: postRepository
) {
    operator fun invoke(
        postId: String,
        postImage: String,
        postDescription: String,
        time: Long,
        userId: String,
        userName: String,
        userImage: String
    ) = repository.uploadPosts(
        postId = postId,
        postImage = postImage,
        postDescription = postDescription,
        time = time,
        userId = userId,
        userName = userName,
        userImage = userImage
    )
}