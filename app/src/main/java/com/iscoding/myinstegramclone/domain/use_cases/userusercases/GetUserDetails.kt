package com.iscoding.myinstegramclone.domain.use_cases.userusercases

import com.iscoding.myinstegramclone.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetails @Inject constructor(
    val repository: UserRepository
) {
    operator fun invoke(userId: String) = repository.getUserDetails(userId = userId)
}