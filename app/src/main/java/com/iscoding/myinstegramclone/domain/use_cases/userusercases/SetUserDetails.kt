package com.iscoding.myinstegramclone.domain.use_cases.userusercases

import com.iscoding.myinstegramclone.domain.repository.UserRepository
import javax.inject.Inject

class SetUserDetails @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(
        userid: String,
        name: String,
        userName: String,
        bio: String,
        siteUrl: String
    ) = repository.setUserDetails(
        userid = userid,
        name = name,
        userName = userName,
        bio = bio,
        siteUrl = siteUrl
    )


}