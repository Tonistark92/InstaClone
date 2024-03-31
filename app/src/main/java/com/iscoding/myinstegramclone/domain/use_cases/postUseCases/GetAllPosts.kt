package com.iscoding.myinstegramclone.domain.use_cases.postUseCases

import com.iscoding.myinstegramclone.domain.repository.postRepository
import javax.inject.Inject

class GetAllPosts @Inject constructor(
    private val repository: postRepository
) {
    operator fun invoke(userId: String) = repository.getAllPosts(userId = userId)
}